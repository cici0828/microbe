function getDeptList() {
	var username = $("#username").val();

	$.ajax({
				type : "post",
				url : "system/getDeptList.action",
				data : "usrnam=" + username,
				success : function(data) {
					var sHtml = "";
					if (data == "") {
						var sblank = '空';
						sHtml = HtmlFmt.selectOptionSel.format('', sblank);

					} else {
						var arrDeptList = data.split(",");

						for (var i = 0; i < arrDeptList.length; i++) {
							if (i == 0) {
								sHtml = HtmlFmt.selectOptionSel.format(
										arrDeptList[i], arrDeptList[i]);
							}

							else
								sHtml = sHtml
										+ HtmlFmt.selectOptionUnSel.format(
												arrDeptList[i], arrDeptList[i]);
						}
					}

					$("#logindept").html(sHtml);
					$("#logindept").select2();
				}
			});
}

function onLogin() {
	var username = $.trim($("#username").val());
	var password = $.trim($("#password").val());
	var loginDept = $.trim($("logindept").val());

	$.ajax({
				type : "post",
				url : "system/login.action",
				data : $("#loginform").serialize()

			});
}