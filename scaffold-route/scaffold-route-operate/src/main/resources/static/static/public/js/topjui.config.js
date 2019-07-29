/**
 * 配置文件说明
 * @type {string}
 * topJUI.language: 消息提示框的中文提示，可根据情况调整
 *
 */
/* 静态演示中获取contextPath，动态演示非必须 开始 */
var contextPath = "";
var remoteHost = "http://localhost:8080";
if (navigator.onLine) {
    remoteHost = "http://demo.ewsd.cn";
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?71559c3bdac3e45bebab67a5a841c70e";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
}
var firstPathName = window.location.pathname.split("/")[1];
if (!(firstPathName == "html" || firstPathName == "json" || firstPathName == "topjui")) {
    contextPath = "/" + firstPathName;
}
/* 静态演示中获取contextPath，动态演示非必须 结束 */

var myConfig = {
    config: {
        pkName: 'uuid', //数据表主键名，用于批量提交数据时获取主键值
        singleQuotesParam: true, //是否对批量提交表格选中记录的参数值使用单引号，默认为false，true:'123','456'，false:123,456
        datagrid: {
            page: 'page', //提交到后台的显示第几页的数据
            size: 'rows', //提交到后台的每页显示多少条记录
            total: 'total', //后台返回的总记录数参数名
            rows: 'rows' //后台返回的数据行对象参数名
        },
        postJson: false, //提交表单数据时以json或x-www-form-urlencoded格式提交，true为json，false为urlencoded
        appendRefererParam: false, //自动追加来源页地址上的参数值到弹出窗口的href或表格的url上，默认为false不追加
        statusCode: {
            success: 200, //执行成功返回状态码
            failure: 300 //执行失败返回状态码
        }
    },
    language: {
        message: {
            title: {
                operationTips: "操作提示",
                confirmTips: "确认提示"
            },
            msg: {
                success: "操作成功",
                failed: "操作失败",
                error: "未知错误",
                checkSelfGrid: "请先勾选中要操作的数据前的复选框",
                selectSelfGrid: "请先选中要操作的数据",
                selectParentGrid: "请先选中主表中要操作的一条数据",
                permissionDenied: "对不起，你没有操作权限",
                confirmDelete: "你确定要删除所选的数据吗？",
                confirmMsg: "确定要执行该操作吗？"
            }
        },
        edatagrid: {
            destroyMsg: {
                norecord: {
                    title: '操作提示',
                    msg: '没有选中要操作的记录！'
                },
                confirm: {
                    title: '操作确认',
                    msg: '确定要删除选中的记录吗？'
                }
            }
        }
    },
    l: 'f1b118fdb4fab92e182c840cc5da1a2cb3ddc904de666b37449d07e99fcf8b6a348f0f92fcf57c720daa430bcfb9aa21213f24c8a656b387e383a7c6bad41d8a0dc5369400deec6a7b6335c56ef6aa26db9b454746b5ea07e37876dc880de424885d02adbb97979daee7cb819435ff92966622aa132b9472a54388d341fcb10d91198797c33110ad191a1e38205027ffb2b1fd91da6db3409178bc4643ebc586eeaf82a0cea7482129d057f636e5324b85afb08230e142f0ef68829236fd83956abd70f5949f0fa525687a6a21b192655031a077f25235488b47bf904100f011c2effd636387bb76cc656870643928f1ea2d704a9c7f903e1aa9fc1211eba4e568c783dbec1f02a1fe09df069853f1761a8f259292fa0ae9e6ef4434c7861fe3c20574af53987c76'
}
