var HtmlFmt = {
	selectOptionSel : "<option value={0} selected>{1}</option>",
	selectOptionUnSel : "<option value={0}>{1}</option>"
};

function getXHRObject()
{
   var xmlhttp;
   if (window.XMLHttpRequest)
     xmlhttp=new XMLHttpRequest();
  else
     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  return xmlhttp;
}

$.extend({
       getXHRObject : function()
		{
		var xmlhttp;
               if (XMLHttpRequest)
                  xmlhttp=new XMLHttpRequest();
               else
                  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
               return xmlhttp;				
		},
			
			myformat : function(src) {
				var result = src;
				if (arguments.length > 0) {
					if (arguments.length == 1 && typeof(args) == "object") {
						for (var key in args) {
							if (args[key] != undefined) {
								var reg = new RegExp("({" + key + "})", "g");
								result = result.replace(reg, args[key]);
							}
						}
					} else {
						for (var i = 1; i < arguments.length; i++) {
							if (arguments[i] != undefined) {
								var reg = new RegExp("({[" + (i - 1) + "]})",
										"g");
								result = result.replace(reg, arguments[i]);
							}
						}
					}
				}

				return result;
			},
			
			loadPic : function(url,elementID,finish){
                 var xhrObject = getXHRObject();
                 xhrObject.onreadystatechange=function event()
                 {
                     if(xhrObject.readyState==4)
                     {
                          if(xhrObject.status==200)
                          {
                             document.getElementById(elementID).src=url;
                             if (typeof finish === 'function') 
                             {
                                 finish();
                             }
                             else if (typeof finish ==='object')
                             {
                             	finish.success();
                             }
                          }
                      }
                  };
                 xhrObject.open("post",url);
                 xhrObject.send(null);				
			}
		});

String.prototype.format = function(src) {
	var result = this;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof(args) == "object") {
			for (var key in args) {
				if (args[key] != undefined) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					var reg = new RegExp("({[" + i + "]})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
	}

	return result;
};

function maxTextArea(a, row) {
	var agt = navigator.userAgent.toLowerCase();
	var is_op = (agt.indexOf("opera") != -1);
	var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
	if (!a) {
		return;
	}
	if (a.value.length  < a.rows)
	{
		a.rows = a.rows + 1;
	}
	/*if (!row)
		row = 5;
	var b = a.value.split("\n");
	var c = is_ie ? 1 : 0;
	c += b.length;
	a.rows = c;
	var d = a.cols;
	if (d <= 20) {
		d = 40;
	}
	for (var e = 0; e < b.length; e++) {
		if (b[e].length >= d) {
			c += Math.ceil(b[e].length / d);
		}
	}
	c = Math.max(c, row);
	if (c != a.rows) {
		a.rows = c;
	}*/
}
