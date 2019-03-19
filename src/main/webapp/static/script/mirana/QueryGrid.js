/**
 * 
 */
var QueryService = {
		buildSearchForm : function(params) {
	        var result = {}
	        for(var prop in params) {
	            if(prop != 'taskId') {
	               result["searchForm." + prop] = params[prop] 
	            } else {
	               result[prop] = params[prop]     
	            }            
	        }
	        return result
	    }	
}

function QueryGrid(config) {
	$.jgrid.defaults.styleUI = 'Bootstrap'
	$.jgrid.styleUI.Bootstrap.base.rowTable = "table table-bordered table-striped"
	$.jgrid.styleUI.Bootstrap.common.icon_base = "fa"
	$.jgrid.styleUI.Bootstrap.base.icon_first = "fa-fast-backward"
	$.jgrid.styleUI.Bootstrap.base.icon_prev = "fa-backward"
	$.jgrid.styleUI.Bootstrap.base.icon_next = "fa-forward"
	$.jgrid.styleUI.Bootstrap.base.icon_end = "fa-fast-forward"
	$.jgrid.styleUI.Bootstrap.base.icon_asc = "fa-sort-asc"
	$.jgrid.styleUI.Bootstrap.base.icon_desc = "fa-sort-desc"
	$.jgrid.styleUI.Bootstrap.base.icon_caption_open = "fa-arrow-up"
	$.jgrid.styleUI.Bootstrap.base.icon_caption_close = "fa-arrow-down"
	$.jgrid.styleUI.Bootstrap.subgrid = {
	    icon_plus : 'fa fa-chevron-right',
	    icon_minus : 'fa fa-chevron-down',
	    icon_open : 'fa fa-chevron-left'
	}
	var rootUrl = $("#rootUrl").attr("href"),
		el = config.el,
		pager = el + "Rooter",
		colModel = config.colModel,
		query = config.query,
		url = rootUrl + config.url;
	var options = {
			height : "auto",
            styleUI : 'Bootstrap',
            emptyrecords : "暂时没有数据",
            shrinkToFit : true,
            cellLayout : 10,
            altRows : false, //是否间隔显示记录
            hoverrows : true,//是否激活Hover事件
            loadonce : false,
            multiselect: true,
            multiboxonly: false,
			autowidth: true,
			pgbuttons : true,//是否显示
			rowNum : 10,//每页的数量
			rownumWidth : 30,
			pginput : true,//是否可以输入页码
			viewrecords: true,//是否显示记录条数
			rowList: [10, 20, 50, 100],
			toppager : false,	//是否在表格顶部也显示分页构件
			pager,
            pagerpos:'center',
            cmTemplate : {
				sortable : true,
		    	resizable : true,
		    	title : false,
		    	fixed : false,
		    	editable : false,//是否可编辑
		    	align : "left"
		    },
		    colModel,
		    prmNames : {
				page : "page", //  当前页数
				rows : "size",//  每页数量
				sort : "sd",// 排序字段
				order : "dirt"// 排序方向
			},
		    datatype: "json",
            jsonReader : {
                //  存放记录位置
                root : "content",
                //  当前页数
                page : "number",
                total : "totalPages",    // 所有的页数
                records : "totalElements", // 所有的记录数
                id : "id",
                repeatitems : false//设置此元素为true,将会容许出现重复元素,但是重复的元素无法同时选中
            },
            url,
           	autoencode : false,//转义服务器传送的数据，将HTML片段转义为普通字符串
			mtype: "GET",//数据的请求方式
			serializeGridData : function(postData) {
				let queryObj = QueryService.buildSearchForm(query())
				return Object.assign(postData, queryObj);
			}            
	}
	$(el).jqGrid(options);
}