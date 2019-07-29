Mock.mock('http://api.topjui.com', 'post', {
    // 属性 list 的值是一个数组，其中含有 1 到 3 个元素
    'list|1-3': [{
        // 属性 sid 是一个自增数，起始值为 1，每次增 1
        'sid|+1': 1,
        // 属性 userId 是一个5位的随机码
        'userId|5': '',
        // 属性 sex 是一个bool值
        "sex|1-2": true,
        // 属性 city对象 是对象值中2-4个的值
        "city|2-4": {
            "110000": "北京市",
            "120000": "天津市",
            "130000": "河北省",
            "140000": "山西省"
        },
        //属性 grade 是数组当中的一个值
        "grade|1": [
            "1年级",
            "2年级",
            "3年级"
        ],
        //属性 guid 是唯一机器码
        'guid': '@guid',
        //属性 id 是随机id
        'id': '@id',
        //属性 title 是一个随机长度的标题
        'title': '@title()',
        //属性 paragraph 是一个随机长度的段落
        'paragraph': '@cparagraph',
        //属性 image 是一个随机图片 参数分别为size, background, text
        'image': "@image('200x100', '#4A7BF7', 'Hello')",
        //属性 address 是一个随机地址
        'address': '@county(true)',
        //属性 date 是一个yyyy-MM-dd 的随机日期
        'date': '@date("yyyy-MM-dd")',
        //属性 time 是一个 size, background, text 的随机时间
        'time': '@time("HH:mm:ss")',
        //属性 url 是一个随机的url
        'url': '@url',
        //属性 email 是一个随机email
        'email': '@email',
        //属性 ip 是一个随机ip
        'ip': '@ip',
        //属性 regexp 是一个正则表达式匹配到的值 如aA1
        'regexp': /[a-z][A-Z][0-9]/,
    }]
});

//产品列表接口数据
Mock.mock(_ctx + 'product/list', 'post', {
    // 属性 list 的值是一个数组，其中含有 1 到 10 个元素
    'rows|20': [{
        // 属性 id 是一个自增数，起始值为 1，每次增 1
        'id|+1': 1,
        'uuid': '@guid()',
        'name|3': '@ctitle',
        'code|1': ['2103', '5103', '1204'],
        'spec|1': ['KC-W200SW', '白色LR-1688BY-2', '银灰色BCD-339WBA 339'],
        'sale_price|580-5800': 580,
        'rate|0-100': 55,
        'sj|1': ['1', '2', '3'],
        'tz|1': ['1,2', '2,3', '3,4', '1,4'],
        'recommend|1': [0, 1],
        'createTime': '@date(2018-01-dd)',
        'thumbnail': '@image(200x200, @hex())',
        'time': '@time',
        'url': '@url("http","topjui.com")',
        'color': '@color()'
    }],
    'total': 38
});
$.post(_ctx + 'product/list', {}, function (res) {
    console.log(res);
}, 'json');

//产品详情接口数据
Mock.mock(_ctx + 'product/detail', 'get', {
    'id|+1': 1,
    'name|3': '@ctitle',
    'code|1': ['2103', '5103', '1204'],
    'spec|1': ['KC-W200SW', '白色LR-1688BY-2', '银灰色BCD-339WBA 339'],
    'sale_price|580-5800': 580,
    'rate|0-100': 55,
    'sj|1': ['1', '2', '3'],
    'tz|1': ['1,2', '2,3', '3,4', '1,4'],
    'recommend|1': [0, 1],
    'createTime': '@date(2018-01-dd)',
    'thumbnail': '@image(200x200, @hex())',
    'remark': '@cparagraph',
    'time': '@time',
    'url': '@url("http","topjui.com")',
    'color': '@color()'
});
$.get(_ctx + 'product/detail', {}, function (res) {
    console.log(res);
}, 'json');

//操作成功数据
Mock.mock(_ctx + 'response/success', 'post', {
    'statusCode': 200,
    'title': '操作提示',
    'message': '操作成功'
});
$.post(_ctx + 'response/success', {}, function (res) {
    console.log(res);
}, 'json');