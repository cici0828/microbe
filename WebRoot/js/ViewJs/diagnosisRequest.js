var slice_li = {
    //sliceHost: 'http://192.168.0.217/slice',
    sliceHost: 'http://ycbl.dazd.cn',
    getSliceShotSnap: function() {
        return this.sliceHost + '/slicefile/%sliceid/preview.jpg';
    },

    getSliceView: function(dsno, barcode, maxzoom, width, height) {
        return (this.sliceHost + '/autologin.html?display=00000000000&sliceId=%sliceid&barcode=%barcode&sliceId2=%sliceid&maxzoom=%maxzoom&width=%width&height=%height')
            .replace(/%sliceid/, dsno).replace(/%maxzoom/g, maxzoom).replace(/%width/g, width)
            .replace(/%height/g, height).replace(/%barcode/g, barcode);
    },

    getSliceView2: function(dsno, barcode, maxzoom, width, height) {
        return (this.sliceHost + '/autologin.html?display=00000000000&sliceId=%sliceid&barcode=%barcode&sliceId2=%sliceid&maxzoom=%maxzoom&width=%width&height=%height')
            .replace(/%sliceid/g, dsno).replace(/%barcode/g, barcode)
            .replace(/%maxzoom/g, maxzoom).replace(/%width/g, width)
            .replace(/%height/g, height);
    },

    deleteSlice: function(barcode) {
        return (this.sliceHost + '/barcodeinfo/deleteCase?barcode=%barcode')
            .replace(/%barcode/, barcode);
    }

}

function viewSliceLogin() {
    if ($('#viewSliceLogin').length == 0) {
        var c = $('<iframe id="viewSliceLogin">');
        $('body').append(c);
        c[0].onload = function() {}
        c.css('display', 'none').attr('src',
            slice_li.getSliceView2('1', '1', 0, 0, 0));
    }
}

$(document).ready(function() {
    autosize($("#DetailPanel textarea"));
    //dazd_prompt();
    //alert($(window).scrollTop());
    //外框架
    viewSliceLogin();

    $('#splitContainer').css("margin", "0px auto").jqxSplitter({
        height: '99%',
        width: 1190,
        showSplitBar: false,
        splitBarSize: 0,
        resizable: false,
        orientation: 'horizontal',
        panels: [{
            size: '40'
        }]
    });
    //内框架
    $('#splitter').jqxSplitter({
        width: '100%',
        showSplitBar: true,
        splitBarSize: '5px',
        resizable: true,
        panels: [{
            size: '50%'
        }, {
            size: '50%'
        }]
    });
    $('#spliter_sub').jqxSplitter({
        width: '100%',
        height: '100%',
        showSplitBar: false,
        resizable: false,
        splitBarSize: 0,
        orientation: 'horizontal',
        panels: [{
            size: '40'
        }, {
            size: '*'
        }]
    });

    createLog(); //日志弹出窗口创建
    //createUpdateWindows();//补充信息弹出窗口创建
    //$("#tabswidget").jqxTabs({  height: '100%', width: '100%' });     

    //数据绑定
    switch (authCode) {
        case 1: //添加数据
            gridViewBand("20,21");
            break;
        case 2: //审核
            gridViewBand("25,26,27,28,29");
            break;
        default: //选择记录
            gridViewBand("35,36,37");
            break;
    }

    //条码处理      
    $("#search_barcode").on("keydown", function(e) {

        if (e.keyCode == 13) {
            var barcode = $("#search_barcode").val();
            //添加一条数据，并刷新数据 OR 查询数据
            switch (authCode) {
                case 1: //添加数据
                    sendDiagnosis(barcode);
                    break;
                default: //选择记录
                    selectBarcode(barcode);
                    break;
            }
        }
    });
});

function getButtonHtml(index) {
    var value = 0;
    switch (authCode) {
        case 1:
            value = 101;
            break;
        case 2:
            value = 90;
            break;
        case 3:
            value = 64;
            break;
    }
    var html = "";
    if ((value & 1) == 1) {
        html += '<a onclick="setDiagnosisConfirm({text:\'确定要提交[]会诊申请?\',callback:setDiagnosisState,back_params:{index:' + index + ',state:25}})" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowthickstop-1-n" title="提交会诊申请"></span></a>';
    }
    if ((value & 2) == 2) {
        html += '<a onclick="setDiagnosisConfirm({text:\'确定要提交[]给专家?\',callback:setDiagnosisState,back_params:{index:' + index + ',state:26}})" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowthickstop-1-n" title="提交专家会诊"></span></a>';
    }
    if ((value & 4) == 4) {
        html += '<a onclick="openUpdateWindows({index:' + index + '})" href="javascript:void(0)"><span class="ui-icon ui-icon-pencil" title="修改信息"></span></a>';
    }
    if ((value & 8) == 8) {
        html += '<a onclick="setDiagnosisConfirm({text:\'退回[]会诊原因\',title:\'退回原因\',okCallback:setDiagnosisState,back_params:{index:' + index + ',state:21}})" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowreturnthick-1-w" title="退回会诊申请"></span></a>';
    }
    if ((value & 16) == 16) {
        html += '<a onclick="setDiagnosisConfirm({text:\'撤消[]会诊原因\',title:\'撤消原因\',okCallback: withdrawalExpert,back_params:{index:' + index + ',state:28}})" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowreturnthick-1-s" title="撤消专家会诊申请"></span></a>';
    }
    if ((value & 32) == 32) {
        html += '<a onclick="confirmReplace({text:\'确定要删除[]此记录?\',callback:deleteDiagnosis,back_params:{index:' + index + '}})" href="javascript:void(0)"><span class="ui-icon ui-icon-closethick" title="删除会诊信息"></span></a>';
    }
    if (value == 64) {
        html += '<a onclick="csltM.resultConfirm(' + index + ')" href="javascript:void(0)"><span class="ui-icon ui-icon-check" title="诊断结果确认"></span></a>';
        html += '<a onclick="csltM.resultBack(' + index + ')" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowreturnthick-1-w" title="退回会诊审核，重新选择专家复核"></span></a>';
        html += '<a onclick="csltM.resultCommit(' + index + ')" href="javascript:void(0)"><span class="ui-icon ui-icon-arrowthickstop-1-n" title="提交读片会"></span></a>';

    }

    if ((value & 64) == 64) {
        html += '<a onclick="bandLog(' + index + '); openLog()" href="javascript:void(0)"><span class="ui-icon ui-icon-lightbulb" title="查看日志"></span></a>';
    }


    return html;
    /* $("a[typeValue]").each(function(i){
        if((value&($(this).attr("typeValue")-0))!=$(this).attr("typeValue")-0){
            $(this).hide();
        }
    }); */
}

var csltM = {
    resultConfirm: function(index) {
        var row = getBindRows()[index];
        var reporttype = ((row.reporttype === '诊断报告') ? 'bg' : 'yj');

        var reportShow = $('<div>');
        reportShow.attr('id', 'reportshow');
        $('body').append(reportShow);
        $('#reportshow').dialog({
            title: "报告确认",
            width: 800,
            height: 500,
            modal: true,
            closed: false,
            cache: false,
            href: 'template/getResultReport.action?barcode=' + row.barcode,
            onClose: function() {
                $('#reportshow').dialog('destroy');
            },
            buttons: [{
                text: '确认',
                width: '75px',
                id: 'confirmOK',
                handler: function() {
                    var isSuccess = ($('.getResultFailure').length === 0);

                    if (isSuccess) {
                        var reporttype = ((row.reporttype === '诊断报告') ? 'bg' : 'yj');
                        $('#reportConfirmWaittig').css({
                            display: 'block',
                        });
                        $.ajax({
                                url: 'cslt/resultConfirm.action?pt_barcode=' + row.barcode + '&pid=' + row.pid + '&reporttype=' + reporttype,
                                type: 'POST'
                            })
                            .done(function() {
                                $('#reportshow').dialog('destroy');
                            })
                            .fail(function() {
                                alert('确认失败!')

                            }).always(function() {
                                $('#reportConfirmWaittig').css({
                                    display: 'none',
                                });
                            });

                    } else {
                        $('#reportshow').dialog('destroy');
                    }

                }
            }, {
                text: '关闭',
                width: '75px',
                id: 'confirmClose',
                handler: function() {
                    $('#reportshow').dialog('destroy');
                }

            }]
        })
    },

    resultBack: function(index) {
        var row = getBindRows()[index];
        var state = row.state;
        if (state == 36) {
            alert('诊断结果已确认，无法退回。');
            return;
        }

        if (state == 37) {
            alert('诊断结果已提交读片会，无法退回。');
            return;
        }

        setDiagnosisConfirm({
            text: '退回[]会诊审核原因',
            title: '退回原因',
            okCallback: setDiagnosisState,
            back_params: { index: index, state: 29 }
        })
    },

    resultCommit: function() {
        alert("[提交读片会]开发中...");
    }

    // function backCase(index, newState){
    //     var row = getBindRows()[index];
    //     var backCaseDialog = $('<div>');
    //     backCaseDialog.attr('id', 'backCaseDialog');
    //     $('body').append(backCaseDialog);
    //     $('#backCaseDialog').dialog({
    //         title: "报告确认",

    //     })
    // }
}


//数据绑定
function gridViewBand(stateStr) {
    var url = "cslt/getDiagnosisList.action?state=" + stateStr;
    var cellsrenderer = function(index, columnfield, value, defaulthtml,
        columnproperties) {
        //allPrpos(row.barcode);
        //var html=' <a href="javascript:void();" onclick="onRowdoubleclick('+row+')" class="a_link"> 查看 </a> ';
        var html = '<div class="ico_box">';
        html += getButtonHtml(index);
        html += '</div>';
        return html;

    };
    var initrowdetails = function(index, parentElement, gridElement, datarecord) {
        var detail_warp = null;
        detail_warp = $($(parentElement).children()[0]); //grid_detail
        if (detail_warp != null) {
            setPic(index, detail_warp);
        }
    };
    //prepare the data
    var source = {
        datatype: "json",
        datafields: [{ name: 'pid', type: 'string' },
            { name: 'barcode', map: 'groupprogram>pt_barcode' },
            { name: 'state_msg', map: 'groupprogram>state_msg', type: 'string' },
            { name: 'ageunit', type: 'string' },
            { name: 'patientname', map: 'patinfo>patientname', type: 'int' },
            { name: 'sex', map: 'patinfo>sex', type: 'string' },
            { name: 'age', map: 'patinfo>age', type: 'string' },
            { name: 'folderno', map: 'patinfo>folderno', type: 'string' },
            { name: 'clinicnme', map: 'patinfo>clinicnme', type: 'string' },
            { name: 'bedno', map: 'patinfo>bedno', type: 'string' },
            { name: 'clinicid', map: 'patinfo>clinicid', type: 'string' },
            { name: 'doctor', map: 'patinfo>doctor', type: 'string' },
            { name: 'samplefrom', map: 'patinfo>samplefrom', type: 'string' },
            { name: 'sampleto', map: 'patinfo>sampleto', type: 'string' },
            { name: 'senddate', map: 'patinfo>senddate', type: 'date' },
            { name: 'diagnosis', map: 'patinfo>diagnosis', type: 'string' },
            { name: 'state', map: 'groupprogram>state', type: 'string' },
            { name: 'commitdate', map: 'groupprogram>commitdate', type: 'date', sortable: true },
            { name: 'picnum', map: 'groupprogram>picnum', type: 'int' },
            { name: 'reportmethod', map: 'patinfo>reportmethod', type: 'string' },
            { name: 'viewtype', map: 'groupprogram>viewtype', type: 'string' },
            { name: 'loginname', map: 'groupprogram>loginname', type: 'string' },
            { name: 'loginid', map: 'groupprogram>loginid', type: 'string' },
            { name: 'logindate', map: 'groupprogram>logindate', type: 'date', sortable: true },
            { name: 'logintel', map: 'groupprogram>logintel', type: 'string' },
            { name: 'bid', map: 'baseinfo>bid', type: 'string' },
            { name: 'clidata', map: 'baseinfo>clidata', type: 'string' },
            { name: 'clihistory', map: 'baseinfo>clihistory', type: 'string' },
            { name: 'gross', map: 'baseinfo>gross', type: 'string' },
            { name: 'first_option', map: 'baseinfo>first_option', type: 'string' },
            { name: 'gid', map: 'baseinfo>gid', type: 'string' },
            { name: 'letter', map: 'baseinfo>letter', type: 'string' },
            { name: 'expertid', map: 'groupprogram>expertid', type: 'int' },
            { name: 'expertname', map: 'groupprogram>expertname', type: 'int' },
            { name: 'reporttype', map: 'groupprogram>reporttype', type: 'string' }
        ],
        url: url,
        pager: function(pagenum, pagesize, oldpagenum) {
            // callback called when a page or page size is changed.
        }
    };

    $("#jqxgrid").on("bindingcomplete", function(event) {
        //-------默认选择
        var barcode = $("#search_barcode").val();
        if (barcode) {
            selectBarcode(barcode);
        } else {
            $("#jqxgrid").jqxGrid("selectrow", 0);
        }

        if (authCode == 1) {
            setInterval(function() {
                var rows = $('#jqxgrid').jqxGrid('getdisplayrows');
                for (var i = 0; i < rows.length; i++) {
                    var barcode = rows[i]['barcode'];

                    (function(b) {
                        //从oralce上获取数据
                        // $.ajax({
                        //     url: "cslt/getSliceUploadState.action?barcode=" + barcode,
                        //     type: "POST",
                        //     dataType: "json",
                        //     success: function(data) {
                        //         var list = eval(data);
                        //         var total = 0;
                        //         var len = list["length"];

                        //         for (var j = 0; j < len; j++) {
                        //             total = parseFloat(list[j]["state"]) + total
                        //         }

                        //         if (len > 0) {
                        //             total = Math.round(total / len);
                        //         }

                        //         $('#case' + b).html(total + '%');
                        //     }
                        // })

                        //从oralce上获取数据
                        $.ajax({
                            url: "cslt/getCsltSliceListFromMySQL.action?barcode=" + barcode,
                            type: "POST",
                            dataType: "json",
                            success: function(data) {
                                var list = eval(data);
                                var total = 0;
                                var len = list["length"];

                                for (var j = 0; j < len; j++) {
                                    total = Math.round(list[j]["offset"] / list[j]["counts"] * 100) + total
                                }

                                if (len > 0) {
                                    total = Math.round(total / len);
                                }

                                $('#case' + b).html(total + '%');
                            }
                        })
                    })(barcode)
                }
            }, 1000);
        }


    });

    var dataAdapter = new $.jqx.dataAdapter(source); //数据对象
    //数据绑定
    $("#jqxgrid").jqxGrid({
        width: "100%",
        height: "100%",
        source: dataAdapter,
        //columnsresize: true,
        //filterable: true,
        //sortable:true,
        pageable: true,
        showtoolbar: true,
        toolbarheight: 30,
        pagesize: 50,
        pagesizeoptions: [30, 40, 50],
        localization: getLocalization('cn'),
        altrows: true,
        //          rowdetails : true,
        //          rowdetailstemplate : {
        //              rowdetails : "<div class='grid_detail'></div>",
        //              rowdetailsheight : 80
        //          },
        //          initrowdetails : initrowdetails,
        columns: [{ text: '功能', width: 80, cellsrenderer: cellsrenderer, align: 'center' },
            { text: '流程状态', datafield: 'state_msg', width: 70, align: 'center', cellsalign: "center" },
            { text: '条码号', datafield: 'barcode', width: 90, align: 'center' },
            { text: '病人姓名', datafield: 'patientname', width: 70, align: 'center' },
            //{ text: '切片数量', datafield: 'picnum', width: 70, align: 'center' }, 
            {
                text: '上传进度',
                width: 70,
                align: 'center',
                cellsalign: "center",
                hideable: true,
                hidden: !(authCode === 1),
                cellsRenderer: function(row, column, value, rowData) {
                    var data = $('#jqxgrid').jqxGrid('getrowdata', row);
                    var barcode = data['barcode'];
                    var o = $('<div>');
                    o.attr('id', 'case' + barcode).addClass('totalProgress').
                    css({
                        'margin-top': '5px',
                        'width': '100%',
                        'text-align': 'center'
                    });
                    return o[0].outerHTML;
                }
            },
            //{ text: '申请人', datafield: 'loginname', width: 75, hideable: true, hidden: (authCode === 1), align: 'center' },
            //{ text: '申请时间', datafield: 'logindate', width: 90, hideable: true, hidden: (authCode === 1), align: 'center', cellsformat: 'yyyy-MM-dd' },            
            { text: '当前专家', datafield: 'expertname', width: 75, hideable: true, hidden: (authCode === 1), align: 'center' },
            { text: '提交专家时间', datafield: 'commitdate', width: 100, hideable: true, hidden: (authCode === 1), align: 'center', cellsformat: 'yyyy-MM-dd' },
            //{ text: '发起人电话', datafield: 'state', width: 60, hideable: true, hidden: (authCode === 1), align: 'center' },
            //{ text: '发起时间', datafield: 'senddate', filtertype: 'date', width: 85, cellsformat: 'yyyy-MM-dd', align: 'center', hideable: true, hidden: (authCode === 1) },
            //{ text: '提交时间', datafield: 'commitdate', filtertype: 'date', width: 160, cellsformat: 'yyyy-MM-dd HH:mm:ss', hidden: true, align: 'center' }

        ],

        rendertoolbar: function(toolbar) {
            var oTitle = $('<div>')
            oTitle.addClass('dazd_title');
            toolbar.append(oTitle)

            switch (authCode) {
                case 1:
                    oTitle.html("会诊申请"); //cslt/getSliceListFromMDB.action
                    var oRefresh = $('<span><a href="javascript: void(0)">刷新</a><span>');
                    oRefresh.css({
                        display: 'inline-block',
                        float: 'right',
                        padding: '5px 8px',
                        'line-height': '30px',
                        color: '#428bca'
                    });
                    oTitle.append(oRefresh);

                    var a = oRefresh.find('a');
                    a.click(function() {
                        // $.ajax({
                        //         url: 'cslt/getSliceListFromMDB.action',
                        //         type: 'POST',
                        //     })
                        //     .done(function() {
                        //         $("#jqxgrid").jqxGrid('updatebounddata');
                        //     })
                        //     .fail(function() {
                        //         alert('刷新失败！')
                        //     })
                        //     .always(function() {});

                        $("#jqxgrid").jqxGrid('updatebounddata');

                    });
                    break;
                case 2:
                    oTitle.html("会诊审核");
                    break;
                case 3:
                    oTitle.html("会诊结果确认");
                    break;
                default:
                    break;
            }


        }
    });

    var uploadStateInterval;
    $("#jqxgrid").on('rowselect', function(event) {

        var rownum = event.args.rowindex;
        var data = event.args.row;
        if (typeof(data) === 'undefined') return;
        var barcode = data.barcode;
        viewDataBind(rownum, data);
        autosize.update($("#DetailPanel textarea"));

        if (authCode == 1) {
            clearInterval(uploadStateInterval);
            uploadStateInterval = setInterval(function() {
                // 从oracle上获取数据
                //   $.ajax({
                //       url: "cslt/getSliceUploadState.action?barcode=" + barcode,
                //       type: "POST",
                //       dataType: "json",
                //       success: function(data) {
                //           var list = eval(data);
                //           for (var i = 0; i < list.length; i++) {
                //               var d = list[i]
                //               $('#slice' + d['sliceid']).html(d['state'] + '%')
                //           }
                //       }
                //   });


                //从MySQL上获取数据
                $.ajax({
                    url: "cslt/getCsltSliceListFromMySQL.action?barcode=" + barcode,
                    type: "POST",
                    dataType: "json",
                    success: function(data) {
                        var list = eval(data);
                        for (var i = 0; i < list.length; i++) {
                            var d = list[i];
                            var sliceParam = {};
                            sliceParam.maxzoom = d['maxzoom'];
                            sliceParam.height = (d["height"] == 0) ? null : d["height"];
                            sliceParam.width = (d["width"] == 0) ? null : d["width"];

                            $('#slice' + d['id']).html(Math.round((d['offset'] / d['counts']) * 100) + '%')
                            $('#slice_state' + d['id']).html(getSliceFileState(d['filestatus'], d['slicenumber'], sliceParam));
                        }
                    }
                });
            }, 1000)
        }
    });
}

function viewDataBind(rownum, data) {
    if (!data) return;
    if (data) {
        $("#view_barcode").html(data.barcode);
        $("#view_folderno").html(data.folderno);
        $("#vieiw_patientname").html(data.patientname);
        $("#view_sex").html(data.sex);
        $("#view_age").html(data.age);
        $("#view_clinicid").html(data.clinicid);
        $("#view_clinicnme").html(data.clinicnme);
        $("#view_bedno").html(data.bedno);
        $("#view_samplefrom").html(data.samplefrom);
        $("#view_sendstuff").html(data.sendstuff);
        $("#view_doctor").html(data.doctor);
        $("#view_senddate").html(data.senddate.toLocaleDateString()); //(DateUtil.Format("yyyy-MM-dd", data.senddate));
        $("#view_reportmethod").html(data.reportmethod);
        $("#view_gross").val(data.gross);
        $("#view_clidata").val(data.clidata);
        $("#view_clihistory").val(data.clihistory);
        $("#view_first_option").val(data.first_option);
        $("#view_diagnosis").val(data.diagnosis);
    }
    bindPicsToTable(data.barcode);
}

function sendDiagnosis(barcode) {
    $.ajax({
        url: "cslt/sendDiagnosis.action",
        type: "POST",
        dataType: "json",
        data: {
            barcode: barcode
        },
        success: function(data) { //成功
            if (!data.respstate) {
                //alert(data.respmsg);
                dazd_alert({ text: data.respmsg });
                selectBarcode(barcode);
            } else {
                $("#jqxgrid").jqxGrid('updatebounddata');
                /* $("#search_barcode").val("");
                selectBarcode(barcode); */
            }
        },
        error: function(data) {}
    });
}

function getBindRows() {
    var rows = $("#jqxgrid").jqxGrid("getboundrows");
    return rows;
}

function selectBarcode(barcode) {
    var rows = getBindRows();
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].barcode == barcode) {
            $("#jqxgrid").jqxGrid("selectrow", i);
            break;
        }
    }
}

function deleteDiagnosisMySQL(barcode) {
    var deleteContainer = $('<iframe>');
    deleteContainer.css('display', 'none');
    $('body').append(deleteContainer);
    deleteContainer[0].onload = function() {
        this.remove();
    }
    deleteContainer.attr('src',
        slice_li.deleteSlice(barcode));
}

function deleteDiagnosis(params) {
    //if (dazd_confirm("确定要删除此记录?")) {
    var rows = getBindRows();
    var barcode = rows[params.index].barcode;
    $.ajax({
        url: "cslt/deleteDiagnosis.action",
        type: "POST",
        dataType: "json",
        data: {
            barcode: barcode
        },
        success: function(data) { //成功
            deleteDiagnosisMySQL(barcode);
            dazd_alert({ text: "删除成功！" });
            $("#jqxgrid").jqxGrid('updatebounddata');
        },
        error: function(data) {
            //alert("删除失败！");
            dazd_alert({ text: "删除失败！" });
        }
    });
    //}
}

function setPic(index, warp) {
    var rows = getBindRows();
    var barcode = rows[index].barcode;
    $.ajax({
        url: "cslt/getCsltSliceList.action",
        type: "POST",
        dataType: "json",
        data: {
            barcode: barcode
        },
        success: function(data) { //成功
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    //var item = $('<li><img src="http://oa.dazd.cn/others/201406/G/1768674612" /></li>');
                    var item = $('<li><img src="cslt/getCsltSliceSmallPic.action?dsid=' + data[i].dsid + '" /></li>');
                    $(warp).append(item);
                }
            } else {
                var item = $('<li><img src="pic/none.jpg" /></li>');
                $(warp).append(item);
            }
        },
        error: function(data) {}
    });
}

function setDiagnosisConfirm(params) {
    var rows = getBindRows();
    var barcode = rows[params.back_params.index].barcode;
    var old_state = rows[params.back_params.index].state;
    //var picnum = rows[params.back_params.index].picnum;
    var picnum = $("#pics_table").jqxDataTable('getRows').length;
    var isset = false;
    var state = params.back_params.state;
    switch (state) {
        case 21:
            if (old_state == 25 || old_state == 27 || old_state == 28 || old_state == 29) {
                isset = true;
                //,dazd_prompt2
                dazd_prompt(params);
                return;
            }
            break;
        case 25:
            if (old_state == 20 || old_state == 21) {
                isset = true;
                var slicelist = $("#pics_table").jqxDataTable('getRows');
                for (var i = 0; i < slicelist.length; i++) {
                    var filestatus = slicelist[i]['filestatus']
                    if (filestatus != 2) break;
                }

                if ((i == slicelist.length) && (slicelist.length != 0))
                    confirmReplace(params)
                else
                    alert('切片未上传完成，无法提交。');
                return;
            }
            break;
        case 26:
            if (old_state == 25 || old_state == 28 || old_state == 27 || old_state == 29) {
                isset = true;
                if (picnum > 0) {
                    //confirmReplace(params);
                    openExpertWindows(params);
                } else {
                    params.text = "没有切片不能提交给专家！";
                    dazd_alert(params);
                }
                return;
            }
            break;
        case 27:
            if (old_state == 26) {
                isset = true;
                //未有处理
            }
            break;
        case 28:
            if (old_state == 26) {
                isset = true;
                dazd_prompt(params);
                return;
            }
            break;
        case 29:
            if (old_state == 35) {
                isset = true;
                dazd_prompt(params);
                return;
            }
            break;
        default:
            isset = false;
            break;
    }
    if (!isset) {
        //alert("当前状态为‘" + rows[params.index].state_msg + "’,不能进行此操作！");
        dazd_alert({ text: "当前状态为‘" + rows[params.back_params.index].state_msg + "’,不能进行此操作！" });
    }
    /*      else{
                params.callback(params.back_params);
            } */
}

function setDiagnosisState(params) { //(index, state,remark) {
    //if(!setDiagnosisConfirm)return;
    var rows = getBindRows();
    var barcode = rows[params.index].barcode;
    var old_state = rows[params.index].state;
    var isset = false;
    var state = params.state;
    var remark = params.dazd_prompt_value ? params.dazd_prompt_value : "";
    $.ajax({
        url: "cslt/setDiagnosis.action",
        type: "POST",
        dataType: "json",
        data: {
            barcode: barcode,
            "csltGroupprogramModel.state": params.state,
            remark: remark
        },
        success: function(data) { //成功
            if (data.respstate) {
                //alert("操作成功！");
                dazd_alert({ text: "操作成功！" });
                $("#jqxgrid").jqxGrid('updatebounddata');
            } else {
                //alert(data.respmsg)
                dazd_alert({ text: data.respmsg });
            }
        },
        error: function(data) {}
    });
}

function closeLog() {
    $("#logWindows").jqxWindow('close');
}

function openLog() {
    $("#logWindows").jqxWindow('open');
}

function createLog() {
    $("#logWindows").jqxWindow({
        autoOpen: false,
        isModal: true,
        // position: 'center, right',
        resizable: false,
        width: 1000,
        height: "80%",
        minWidth: 200,
        //minHeight: 200,
        maxWidth: 1200,
        position: "center,center",
        maxHeight: 800,
        showCloseButton: true
    });
}

function bandLog(index) {
    var rows = getBindRows();
    var barcode = rows[index].barcode;
    var row = rows[index];
    var key_v = "<strong>操作日志：" + row.patientname + " | " + (row.age ? row.age : "-") + (row.ageunit ? row.ageunit : "岁") + " | " + (row.sex ? row.sex : "未知") + " | " + row.barcode + "</strong>";
    $("#logWindows").jqxWindow({ title: key_v });

    var url = "cslt/getCsltLogList.action?barcode=" + barcode;
    var source = {
        datatype: "json",
        datafields: [{ name: 'logid', type: 'int' },
            { name: 'barcode', type: 'string' },
            { name: 'op_userid', type: 'int' },
            { name: 'op_username', type: 'string' },
            { name: 'op_state_mean', type: 'string' },
            { name: 'op_bz', type: 'string' },
            { name: 'op_date', type: 'date', sortable: true }
        ],
        id: 'id',
        url: url,
        pager: function(pagenum, pagesize, oldpagenum) {
            // callback called when a page or page size is changed.
        }
    };
    var dataAdapter = new $.jqx.dataAdapter(source); //数据对象
    //数据绑定
    $("#logGrid").jqxGrid({
        width: "100%",
        height: "99%",
        source: dataAdapter,
        columnsresize: true,
        //sortable:true,
        pageable: true,
        pagesize: 50,
        localization: getLocalization('cn'),
        altrows: true,
        columns: [{ text: 'ID', datafield: 'logid', width: 40 },
            { text: '条码号', datafield: 'barcode', width: 100 },
            { text: '操作用户ID', datafield: 'op_userid', width: 100 },
            { text: '操作用户名', datafield: 'op_username', width: 90 },
            { text: '操作', datafield: 'op_state_mean', width: 100 },
            { text: '说明', datafield: 'op_bz', minwidth: 160 },
            { text: '操作时间', datafield: 'op_date', filtertype: 'date', width: 160, cellsformat: 'yyyy-MM-dd HH:mm:ss' }
        ]
    });
}

function closeUpdateWindows() {
    $("#dazd-update-windows").dialog("destroy");
}

function openUpdateWindows(data) {
    //data.title="信息编辑";
    createUpdateWindows(data);
}

function createUpdateWindows(data) {
    //data.title=data.title?data.title:"新窗口";
    var row = getBindRows()[data.index];
    var barcode = row.barcode;
    if (!barcode) return;
    var updata_windows = $("<div>", {
        id: "dazd-update-windows",
        css: {
            padding: "5px 10px"
        }
    }).appendTo($("body"));

    //达扬诺维奇|19岁|女|133900291   patientname,age,sex,barcode,ageunit
    updata_windows.dialog({
        title: row.patientname + " | " + (row.age ? row.age : "-") + (row.ageunit ? row.ageunit : "岁") + " | " + (row.sex ? row.sex : "未知") + " | " + barcode,
        width: 800,
        height: 500,
        closed: false,
        cache: false,
        top: 0,
        //href: 'get_content.php',
        href: "template/diagnosis_infopanel.action",
        modal: true,
        buttons: [{
            text: '确定',
            width: 75,
            handler: function() {
                $.ajax({
                    url: "cslt/setCsltBaseinfo.action",
                    type: "POST",
                    dataType: "json",
                    data: {
                        barcode: barcode,
                        clidata: $("#clidata").val(),
                        clihistory: $("#clihistory").val(),
                        first_option: $("#first_option").val(),
                        gross: $("#gross").val()
                    },
                    success: function(data) {
                        if (data.respstate) {
                            dazd_alert({ text: "保存成功！" });
                        }
                    },
                    error: function(data) {
                        //alert(data);
                    },
                    complete: function() {
                        $("#jqxgrid").jqxGrid('updatebounddata');
                        closeUpdateWindows();
                    }
                });
            }
        }, {
            text: '取消',
            width: 75,
            handler: function() {
                closeUpdateWindows();
            }
        }],
        onLoad: function() {
            if (row.clidata) { $("#clidata").val(row.clidata); }
            if (row.clihistory) { $("#clihistory").val(row.clihistory); }
            if (row.first_option) { $("#first_option").val(row.first_option); }
            if (row.gross) { $("#gross").val(row.gross); }
            autosize(updata_windows.find("textarea"));
            autosize.update(updata_windows.find("textarea"));
        },
        onClose: function() {
            closeUpdateWindows();
        }
    });
}

//替换信息cconfirm
function confirmReplace(params) {
    if (params && params.back_params && params.back_params.index >= 0) {
        var row = getBindRows()[params.back_params.index];
        var key_v = " [" + row.patientname + " | " + (row.age ? row.age : "-") + (row.ageunit ? row.ageunit : "岁") + " | " + (row.sex ? row.sex : "未知") + " | " + row.barcode + "] ";

        params.text = params.text.replace("[]", key_v);
    } else {
        params.text = params.text.replace("[]", "");
    }

    dazd_confirm(params);
}

function getSliceFileState(filestatus, slicenumber, sliceParam) {
    console.log(sliceParam);
    if (filestatus == 2) {
        return '<a target="_blank" href="' + (slice_li.getSliceView(slicenumber, null, sliceParam.maxzoom, sliceParam.width, sliceParam.height)).replace(/%sliceid/g, slicenumber) + '"><img width="70" height="70" src=' +
            (slice_li.getSliceShotSnap()).replace(/%sliceid/, slicenumber) + ' /></a>';
    } else if (filestatus == 1) {
        return '解析中';
    } else if (filestatus == 0) {
        return '上传中';
    } else if (filestatus == -2) {
        return '上传失败';
    }
}

function bindPicsToTable(barcode) {
    dazd_load_open();
    $("#pics_table").jqxDataTable("clear");
    var source = {
        dataType: "json",
        dataFields: [
            { name: 'id', type: 'int' },
            { name: 'slicenumber', type: 'string' },
            { name: 'barcode', type: 'string' },
            { name: 'diagnosecomment', type: 'string' },
            { name: 'filestatus', type: 'int' },
            { name: 'maxzoom', type: 'int' },
            { name: 'width', type: 'int' },
            { name: 'height', type: 'int' }
        ],
        id: 'id',
        url: "cslt/getCsltSliceListFromMySQL.action?barcode=" + barcode
    };
    var dataAdapter = new $.jqx.dataAdapter(source);
    $("#pics_table").jqxDataTable({
        width: '100%',
        //autoHeight: true,
        altRows: true,
        source: dataAdapter,
        localization: getLocalization('cn'),
        columns: [{
                text: '#',
                align: "center",
                cellsalign: "center",
                width: 40,
                cellsRenderer: function(row, column, value, rowData) {
                    return (row + 1).toString();
                }
            }, {
                text: '缩略图',
                align: "center",
                cellsalign: "center",
                width: 80,
                cellsRenderer: function(row, column, value, rowData) {
                    var filestatus = rowData['filestatus'];

                    var dsno = rowData['id'];
                    var o = $('<div>');
                    var sliceParam = {};
                    sliceParam.maxzoom = rowData['maxzoom'];
                    sliceParam.height = (rowData["height"] == 0) ? null : rowData["height"];
                    sliceParam.width = (rowData["width"] == 0) ? null : rowData["width"];
                    o.attr('id', 'slice_state' + dsno).addClass('slice_state').
                    css({
                        'width': '70px',
                        'height': '70px',
                        'text-align': 'center',
                        'line-height': '65px',
                    }).html(getSliceFileState(filestatus, rowData['slicenumber'], sliceParam));
                    return o[0].outerHTML;
                }
            },
            { text: '切片名', align: "center", dataField: 'slicenumber', minWidth: 100 },
            { text: '切片描述', align: "center", dataField: 'diagnosecomment', minWidth: 100 }, {
                text: '上传进度',
                cellsalign: "center",
                align: "center",
                width: 75,
                cellsRenderer: function(row, column, value, rowData) {
                    var dsno = rowData['id'];
                    var o = $('<div>');
                    o.attr('id', 'slice' + dsno).addClass('slice').
                    css({
                        'margin-top': '5px',
                        'width': '100%',
                        'text-align': 'center'
                    });
                    return o[0].outerHTML;
                }
            // }, {
            //     text: '功能',
            //     cellsalign: "center",
            //     align: "center",
            //     width: 0,
            //     cellsRenderer: function(row, column, value, rowData) {
            //         return '<a href="javascript:void(0)" >删除</href>';
            //     }
            }
        ]
    });

    var pics_table = $("#pics_table");

    pics_table.on("bindingComplete", function(event) {
        var rows = $("#pics_table").jqxDataTable('getRows');
        if (rows.length > 0)
            pics_table.jqxDataTable({ height: null });
        else
            pics_table.jqxDataTable({ height: "60px" });

        dazd_load_close();
        /*          //详细信息panel
                    $("#DetailPanel").jqxPanel({
                        width : "100%",
                        height : "100%",
                    }); */
    });
}

function bindPicsToTable2(barcode) {
    dazd_load_open();
    $("#pics_table").jqxDataTable("clear");
    var source = {
        dataType: "json",
        dataFields: [
            { name: 'dsid', type: 'int' },
            { name: 'dsno', type: 'string' },
            { name: 'diancode', type: 'string' },
            { name: 'slicedesc', type: 'string' }
        ],
        id: 'id',
        url: "cslt/getCsltSliceList.action?barcode=" + barcode
    };
    var dataAdapter = new $.jqx.dataAdapter(source);
    $("#pics_table").jqxDataTable({
        width: '100%',
        //autoHeight: true,
        altRows: true,
        source: dataAdapter,
        localization: getLocalization('cn'),
        columns: [{
                text: '#',
                align: "center",
                cellsalign: "center",
                width: 40,
                cellsRenderer: function(row, column, value, rowData) {
                    return (row + 1).toString();
                }
            }, {
                text: '缩略图',
                align: "center",
                dataField: 'dsid',
                width: 80,
                cellsRenderer: function(row, column, value, rowData) {
                    //返回图片
                    //return '<img width="70" height="70" src="cslt/getCsltSliceSmallPic.action?dsid=' + value + '" />';

                    return '<a target="_blank" href="' + slice_li.getSliceView().replace(/%sliceid/g, rowData['dsno']) + '"><img width="70" height="70" src=' +
                        slice_li.getSliceShotSnap().replace(/%sliceid/, rowData['dsno']) + ' /></a>';
                }
            },
            { text: '切片名', align: "center", dataField: 'dsno', minWidth: 100 },
            { text: '切片描述', align: "center", dataField: 'slicedesc', minWidth: 100 }, {
                text: '上传进度',
                cellsalign: "center",
                align: "center",
                width: 75,
                cellsRenderer: function(row, column, value, rowData) {
                    var dsno = rowData['dsno'];
                    var o = $('<div>');
                    o.attr('id', 'slice' + dsno).addClass('slice').
                    css({
                        'margin-top': '5px',
                        'width': '100%',
                        'text-align': 'center'
                    });
                    return o[0].outerHTML;
                }
            }, {
                text: '功能',
                cellsalign: "center",
                align: "center",
                width: 50,
                cellsRenderer: function(row, column, value, rowData) {
                    return '<a href="javascript:void(0)" >删除</href>';
                }
            }
        ]
    });

    var pics_table = $("#pics_table");

    pics_table.on("bindingComplete", function(event) {
        var rows = $("#pics_table").jqxDataTable('getRows');
        if (rows.length > 0)
            pics_table.jqxDataTable({ height: null });
        else
            pics_table.jqxDataTable({ height: "60px" });

        dazd_load_close();
        /*          //详细信息panel
                    $("#DetailPanel").jqxPanel({
                        width : "100%",
                        height : "100%",
                    }); */
    });
}

function withdrawalExpert(data) {
    var row = getBindRows()[data.index];
    var barcode = row.barcode;
    var folderno = row.folderno;
    var vstate = data.state;
    var bz = data.dazd_prompt_value;
    dazd_load_open();
    $.ajax({
        url: "cslt/execPtyClstExpertCommit.action",
        type: "POST",
        dataType: "json",
        data: {
            barcode: barcode,
            vnum: 1,
            vstate: vstate,
            bz: bz,
            expertcslt: "",
            folderno: folderno
        },
        success: function(data) {
            if (data.respstate) {
                dazd_alert({ text: "操作成功！" });
                $("#jqxgrid").jqxGrid('updatebounddata');
            }
        },
        error: function(data) {
            //allPrpos(data);
            dazd_alert({ text: "操作失败！" });
        },
        complete: function() {
            dazd_load_close();
        }
    });
}

function closeExpertWindows() {
    //$("#dazd-expert-windows").dialog("destroy");
    //$("#dazd-expert-windows").dialog("close");
    $("#dazd-expert-windows").dialog({ closed: true });
}

function openExpertWindows(data) {
    if ($("#dazd-expert-windows").length == 0) {
        //data.title="信息编辑";
        selectExpertWindows(data);
    } else {
        var row = getBindRows()[data.back_params.index];
        var barcode = row.barcode;
        $("#dazd-expert-windows").dialog({
            title: row.patientname + " | " + (row.age ? row.age : "-") + (row.ageunit ? row.ageunit : "岁") + " | " + (row.sex ? row.sex : "未知") + " | " + barcode,
            closed: false
        });
    }
}
//选择专家
function selectExpertWindows(data) {
    //      var rows = getBindRows();
    //      var barcode = rows[params.back_params.index].barcode;
    //data.title=data.title?data.title:"新窗口";
    var row = getBindRows()[data.back_params.index];
    var barcode = row.barcode;
    if (!barcode) return;
    var updata_windows = $("<div>", {
        id: "dazd-expert-windows",
        css: {
            padding: "5px 10px"
        }
    }).appendTo($("body"));

    //达扬诺维奇|19岁|女|133900291   patientname,age,sex,barcode,ageunit
    updata_windows.dialog({
        title: row.patientname + " | " + (row.age ? row.age : "-") + (row.ageunit ? row.ageunit : "岁") + " | " + (row.sex ? row.sex : "未知") + " | " + barcode,
        width: 800,
        height: 480,
        closed: false,
        cache: false,
        top: 0,
        //href: 'get_content.php',
        href: "template/diagnosis_expert.action",
        modal: true,
        buttons: [{
            text: '确定',
            width: 75,
            handler: function() {
                var expert_rowid = $('#expertGrid').jqxGrid('getselectedrowindex');
                var expert_row = $('#expertGrid').jqxGrid('getrowdata', expert_rowid);
                if (expert_rowid == -1) {
                    dazd_alert({ text: "请选择专家！" });
                    return;
                }
                //allPrpos(expert_row);
                dazd_load_open();
                $.ajax({
                    url: "cslt/execPtyClstCommitExpert.action",
                    type: "POST",
                    dataType: "json",
                    data: {
                        barcode: barcode,
                        vpid: row.pid,
                        vexpertname: expert_row.expertname,
                        vexperid: expert_row.id,
                        vnum: 1,
                        vreporttype: $("#vreporttype").val(),
                        vstate: data.back_params.state,
                        issendsms: 0,
                        vfee: $("#diagnosis_fee").val()
                    },
                    success: function(data) {
                        if (data.respstate) {
                            dazd_alert({ text: "操作成功！" });
                            closeExpertWindows();
                            $("#jqxgrid").jqxGrid('updatebounddata');
                        }
                    },
                    error: function(data) {
                        dazd_alert({ text: "操作失败！" });
                        //$("#jqxgrid").jqxGrid('updatebounddata');
                    },
                    complete: function() {
                        dazd_load_close();
                    }
                });
            }
        }, {
            text: '取消',
            width: 75,
            handler: function() {
                closeExpertWindows();
            }
        }],
        onLoad: function() {
            expertBind();
        },
        onClose: function() {
            closeExpertWindows();
        }
    });
}

function viewExpertDetail(){
    alert('该专家详细资料，正在收集维护中...');
}

function expertBind() {
    var cellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
        var html = ' <center><a href="javascript:void();" onclick="viewExpertDetail()" class="a_link"> 查看 </a></center> ';
        return html;
    };
    //var key=$("#expertKey").val();
    var url = "cslt/getExpertList.action";
    var source = {
        datatype: "json",
        datafields: [{ name: 'id', type: 'int' },
            { name: 'expertname', type: 'string' },
            { name: 'dept', type: 'string' },
            { name: 'tel', type: 'string' },
            { name: 'nature', type: 'string' },
            { name: 'viewnum', type: 'int' },
            { name: 'fee', type: 'float' },
            { name: 'iswork', type: 'string' },
            { name: 'finishdate', type: 'date', sortable: true },
            { name: 'area', type: 'string' },
            { name: 'specialty', type: 'string' }
        ],
        id: 'id',
        url: url,
        pager: function(pagenum, pagesize, oldpagenum) {
            // callback called when a page or page size is changed.
        }
    };
    var dataAdapter = new $.jqx.dataAdapter(source); //数据对象
    //数据绑定
    $("#expertGrid").jqxGrid({
        width: "100%",
        height: "350",
        source: dataAdapter,
        //columnsresize : true,
        //sortable:true,
        //pageable : true,
        //pagesize : 20,
        showtoolbar: true,
        toolbarheight: 35,
        localization: getLocalization('cn'),
        altrows: true,
        columns: [{ text: '', datafield: 'id', width: 40, cellsrenderer: cellsrenderer },
            { text: '专家姓名', datafield: 'expertname', width: 100 },
            { text: '联系方式', datafield: 'tel', width: 100 },
            { text: '所属中心', datafield: 'dept', width: 90 },
            { text: '工作性质', datafield: 'nature', width: 80 },
            { text: '在看片数', datafield: 'viewnum', width: 60 },
            { text: '在看片数预计完成时间', datafield: 'finishdate', filtertype: 'date', width: 160, cellsformat: 'yyyy-MM-dd' },
            { text: '是否在岗', datafield: 'iswork', minwidth: 60 },
            { text: '', datafield: 'area', minwidth: 160, hidden: true },
            { text: '专家服务', datafield: 'specialty', minwidth: 160, hidden: true }
        ],
        rendertoolbar: function(toolbar) {
            var container = $('<div class="form-group tips"></div>');
            var label = $('<label for="">所属专业：</label>');
            var select = $('<select id="expert_key" name="expert_key" /></select>');

            $('<option value="">全部</option>').appendTo(select);
            $('<option value="乳腺病理">乳腺病理</option>').appendTo(select);
            $('<option value="甲状腺病理">甲状腺病理</option>').appendTo(select);
            $('<option value="皮肤病理">皮肤病理</option>').appendTo(select);
            $('<option value="神经系统病理">神经系统病理</option>').appendTo(select);
            $('<option value="呼吸系统病理">呼吸系统病理</option>').appendTo(select);
            $('<option value="消化系统病理">消化系统病理</option>').appendTo(select);
            $('<option value="腹膜及腹膜后病理">腹膜及腹膜后病理</option>').appendTo(select);
            $('<option value="骨软组织病理">骨软组织病理</option>').appendTo(select);
            $('<option value="妇产科病理">妇产科病理</option>').appendTo(select);
            $('<option value="肾脏病理">肾脏病理</option>').appendTo(select);
            $('<option value="淋巴造血系统病理">淋巴造血系统病理</option>').appendTo(select);
            $('<option value="泌尿系统病理">泌尿系统病理</option>').appendTo(select);
            $('<option value="耳鼻喉病理">耳鼻喉病理</option>').appendTo(select);
            $('<option value="纵膈病理">纵膈病理</option>').appendTo(select);
            $('<option value="内分泌病理">内分泌病理</option>').appendTo(select);
            $('<option value="肝胆、胰腺病理">肝胆、胰腺病理</option>').appendTo(select);
            $('<option value="涎腺病理">涎腺病理</option>').appendTo(select);
            $('<option value="细胞病理">细胞病理</option>').appendTo(select);
            toolbar.append(container);
            container.append(label);
            container.append(select);
            select.on("change", function() {
                addfilter(select.val());
            });
        }
    });

    $("#expertGrid").on('rowselect', function(event) {
        var rownum = event.args.rowindex;
        var data = event.args.row;
        $("#diagnosis_fee").val(data.fee);
    });
}

function addfilter(value) {
    if (value == "") {
        $("#expertGrid").jqxGrid('clearfilters');
        return;
    }
    var filtergroup = new $.jqx.filter();
    var filter_or_operator = 1;
    var filtervalue = value;
    var filtercondition = 'contains';
    var filter1 = filtergroup.createfilter('stringfilter', filtervalue, filtercondition);
    filtergroup.addfilter(filter_or_operator, filter1);
    // add the filters.
    $("#expertGrid").jqxGrid('addfilter', 'specialty', filtergroup);
    // apply the filters.
    $("#expertGrid").jqxGrid('applyfilters');
}
