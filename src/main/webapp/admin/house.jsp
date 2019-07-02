<%--
  Created by IntelliJ IDEA.
  User: xlj
  Date: 2019/6/28
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出租房屋</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function(){
            //使用datagrid绑定数据展示
            $('#ho').datagrid({
                url:'/houseNoPass',
                fitColumns: true,
                pagination: true,
                pageList: [5, 10, 15, 20],
                toolbar:"#ToolBar",
                pageSize:5,
                columns: [[
                    {field:'ck',checkbox:true},  //复选框列
                    { field: 'id', title: '编号', width: 50, align: "center" },
                    { field: 'title', title: '标题', width: 50, align: "center" },
                    { field: 'dname', title: '区域', width: 50, align: "center" },
                    { field: 'sname', title: '街道', width: 50, align: "center" },
                    { field: 'tname', title: '类型', width: 50, align: "center" },
                    { field: 'price', title: '价格', width: 50, align: "center" },
                    { field: 'floorage', title: '面积', width: 50, align: "center" },
                    { field: 'ispass', title: '状态', width: 50, align: "center" ,
                            formatter:function (value,row,index) {
                                return "未审核"
                            }
                    },

                    { field: 'opt', title: '操作', width: 50, align: "center",
                        formatter: function(value,row,index){
                            //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                            return "<a href='javascript:checkPass("+row.id+")'>审核</a>";
                        }
                    }
                ]]
            });
        });
        //实现搜索
        function searchUser(){
            //datagrid控制重新加载的方法
            //$("#dg").datagrid("load",跟查询条件的参数);
            var $name=$("#name").val();
            var $telephone=$("#telephone").val();
            $("#ho").datagrid("load",{"name":$name,"telephone":$telephone});
        }
        //审核通过的方法
        function checkPass(id){
            //发布异步请求
            $.post("/passhouse",{"id":id},function(data){
                if(data.result>0){
                    $.messager.alert('提示框','恭喜通过成功!');
                    $("#ho").datagrid("reload");
                }else{
                    $.messager.alert('提示框','审核失败!');
                }
            },"json");

        }
    </script>
</head>
<body>
<table id="ho"></table>

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
    <div>
        姓名：<input type="text" name="name" id="name"/>
        电话：<input type="text" name="telephone" id="telephone"/>
        <a id="btn" href="javascript:searchUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
</div>
</body>
</html>
