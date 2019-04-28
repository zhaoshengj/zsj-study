//是否显示菜单框
function showItem(menu){
    if(menu.style.display == "none"){
        menu.style.display = "";
    }else{
        menu.style.display = "none";
    }
}

function show()  //显示隐藏层和弹出层
{
    container.style.display="block";  //显示隐藏层
    container.style.height="50%";  //设置隐藏层的高度为当前页面高度
    container.style.opacity=0.5;
    document.getElementById("hidebox").style.display="block";  //显示弹出层
}
function hide()  //去除隐藏层和弹出层
{
    document.getElementById("hidebox").style.display="none";
    container.style.opacity=1;
}