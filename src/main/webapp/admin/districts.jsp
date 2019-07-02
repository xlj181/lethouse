<%--
  Created by IntelliJ IDEA.
  User: xlj
  Date: 2019/6/18
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function(){
            //使用datagrid绑定数据展示
            $('#dg').datagrid({
                url:'/allD',
                fitColumns: true,
                pagination: true,
                pageList: [5, 10, 15, 20],
                toolbar:"#ToolBar",
                pageSize:5,
                columns: [[
                    {field:'ck',checkbox:true},  //复选框列
                    { field: 'id', title: '编号', width: 50, align: "center" },
                    { field: 'name', title: '名称', width: 50, align: "center" },
                    { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                return "<a href='javascript:delSingle("+row.id+")'>删除</a>" +
                    " |" +
                    " <a href='javascript:openStreetDialog("+row.id+")'>查看街道</a>";
                        }
                 }
                ]]
            });
        });

        //打开添加窗口
        function Add() {
            // alert("打开窗口");
            $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
        }
        //关闭添加窗口
        function CloseDialog(obj){
            $("#"+obj).dialog("close");
        }
        //保存
        function SaveDialog(){
            //跳转到后台实现保存
            //传统:取值-->通过$.ajax方法发送异步请求实现添加
            $('#saveDialogForm').form('submit', {
                url:"/insertD",  //提交的服务器地址
                success:function(data){ //成功的回调函数
                    //data接收服务器返回值json字符串（不是json对象）
                    var obj=$.parseJSON(data);
                    if(obj.result>0){
                        $("#AddDialog").dialog("close");  //关闭
                        $("#dg").datagrid("reload");  //刷新
                        //alert("添加成功");
                        $.messager.alert('提示框','恭喜添加成功!');
                    }
                    else
                    {
                        $.messager.alert('提示框','系统正在维护!');
                    }
                }
            });
        }
        //弹出修改对话框，
        function ModifyBySelect(){
            //判断有没有选择修改的记录
            //获取所有选中行，返回的数据，如果没有选中返回空
            //alert(12);
            var SelectRows = $("#dg").datagrid('getSelections');
            if(SelectRows.length!=1){
                $.messager.alert('提示框','你还没有选中行，或者选择了多行');
                return;
            }
            //选择了一行
            //还原数据
            var SelectRow = SelectRows[0];  //获取当前行的json:{"id":1000,"name":"丰台"}
            //打开编辑对话框
            $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
            //获得行对象的数据加载到表单中显示
            //注意:SelectRow表示的就是当前行的Json:{"id":1000,"name":"丰台"}
            // 表单对象名称与json对象的键相同即可还原
            $("#upDialogForm").form('load',SelectRow);
        }
        //修改
        function upDialog(){
            //跳转到后台实现保存
            //传统:取值-->通过$.ajax方法发送异步请求实现添加
            $('#upDialogForm').form('submit', {
                url:"/updateD",  //提交的服务器地址
                success:function(data){ //成功的回调函数
                    //data接收服务器返回值json字符串（不是json对象）
                    var obj=$.parseJSON(data);
                    if(obj.result>0){
                        $("#upDialog").dialog("close");  //关闭
                        $("#dg").datagrid("reload");  //刷新
                        $.messager.alert('提示框','恭喜修改成功!');
                    }
                    else
                    {
                        $.messager.alert('提示框','系统正在维护!');
                    }
                }
            });
        }

        /*打开显示街道的对话框*///删除
        //删除单个区域
        function delSingle(id){
            $.messager.confirm('确认提示框', '你真的想删除我吗?', function(result){
                if (result){
                    // 实现删除  发送异步请求实现删除
                    //alert(id);
                    //$.post("服务器地址",给服务器传参,回调函数,"json");
                    //传参的格式: {"参数名称":值,"参数名称":值}
                    $.post("/deleteD",{"id":id},function(data){
                        if(data.result>0){
                            $("#dg").datagrid('reload'); //刷新
                            //alert("添加成功");
                            //$.messager.alert('提示框','恭喜删除成功!');
                        }
                        else {
                            $.messager.alert('提示框','系统正在维护!');
                        }
                    },"json");
                }
            });
        }
        /*删除选中项：批量删除*/
        function DeleteMore(){
            //判断有无选中行
            var SelectRows = $("#dg").datagrid('getSelections');
            if(SelectRows.length==0){
                $.messager.alert('提示框','你还没有选中行,操作过细点');
                return;
            }

            //获取选中项的值   拼成:1,2,3
            var delValue="";
            for(var i=0;i< SelectRows.length;i++){
                delValue=delValue+SelectRows[i].id+",";
            }
            delValue=delValue.substring(0,delValue.length-1);
            //alert(delValue);

            //发送异步请求到服务器
            $.post("/deleteMD",{"ids":delValue},function(data){
                if(data.result>0){
                    $("#dg").datagrid('reload'); //刷新
                    //alert("添加成功");
                    $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
                }
                else
                {
                    $.messager.alert('提示框','系统正在维护!');
                }
            },"json");
        }
        function openStreetDialog(obj){  //obj接收区域编号
            //显示对话框
            $("#streetDialog").dialog("open").dialog('setTitle',"街道信息");
            //加载对话框中展示的街道  发请求显示数据

            $('#streetDg').datagrid({
                title:">>>>街道信息",
                url:'/getStreetByDid?did='+obj,
                fitColumns: true,
                //queryParams: {"did": obj},  //发送参数
                pagination: true,
                pageList: [5, 10, 15, 20],
                //toolbar:"#ToolBar",
                //singleSelect:true,  //只能设置单选
                pageSize:5,
                columns: [[
                    {field:'ck',checkbox:true},  //复选框列
                    { field: 'id', title: '编号', width: 50, align: "center" },
                    { field: 'name', title: '名称', width: 50, align: "center" },
                    { field: 'opt', title: '操作', width: 50, align: "center",
                        formatter: function(value,row,index){
                            //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                            return "<a href='javascript:delSingle(\"+row.id+\")'>删除</a> | <a href='javascript:openStreetDialog(\"+row.id+\")'>查看街道</a>";
                        }
                    }
                ]]
            });
            //将区域编号存于表单中,以便后期添加街道
            $("#districtId").val(obj);
        }
    </script>

</head>
<body>
<!--展示数据-->
<table id="dg"></table>

<!--定义工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:DeleteMore()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>

<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="saveDialogForm" method="post">
        <table>

            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name"  /></td>
            </tr>

        </table>
    </form>
</div>

<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">添加</a>
    <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<!--修改对话框-->
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 300px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" id="author" /></td>
            </tr>
            <tr>

                <td><input type="hidden" name="id"  /></td>
            </tr>
        </table>
    </form>
</div>


<!--给修改对话框添加修改按钮-->
<div id="upDialogButtons">
    <a href="javascript:upDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('upDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<!--在区域查看街道的对话框-->
<div id="streetDialog" class="easyui-dialog"<%-- buttons="#upDialogButtons"--%>
     style="width: 600px; height: 450px; padding: 10px 20px;" closed="true">
    <table id="streetDg"></table>
    <hr/>
    <form>
        街道名称:<input type="text">
        区域id:<input type="text" id="districtId">
        <input type="button" value="添加">

    </form>
</div>
</body>
</html>
