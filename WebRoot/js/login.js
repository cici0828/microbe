$(document).ready(function() {
    $("#logindept").jqxDropDownList({
        width: '100%',
        height: 34,
        source: [''],
        selectedIndex: 0
    });

    $("#username").blur(function() {
        $.ajax({
                url: 'system/getDeptList.action',
                type: 'POST',
                data: { usrnam: $('#username').val() },
            })
            .done(function(data) {
                var deptList = data.split(',');
                $("#logindept").jqxDropDownList({
                    source: deptList,
                    selectedIndex: 0
                })
            })
    });

    $("#btnLogin").click(function() {
        var usrnam = $('#username').val();
        var password = $('#password').val();
        var logindept = $("#logindept").val();

        $.ajax({
                url: 'system/login2.action',
                type: 'POST',
                data: {
                    "userCustom.usrnam": usrnam,
                    "passwd": password,
                    "userCustom.logindept": logindept
                },
            })
            .done(function(data) {
                if (data == 1) {
                    location.href = 'mainframe.action';
                } else {
                    alert("用户名或密码错误，请重新输入！")
                }
            });
    });
});
