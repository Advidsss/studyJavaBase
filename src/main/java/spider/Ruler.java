package spider;
/*
* Created by  WangDi  on 2017/8/31
*
*/

/*
*定义查询过程中所需要的信息，方便扩展和代码的重用,不可能针对特定的网页单独写一套代码
*/
public class Ruler {

    //爬取链接
    private String url;

    //参数集合
    private String[] params;

    //参数对应的值
    private String[] values;

    //过滤标签
    private String resultTagName;

    private int type = ID ;

    private int requestMoethod = GET ;

    //请求方式两种
    public final static int GET = 0 ;
    public final static int POST = 1 ;

    //根据class、id、select标签筛选
    public final static int CLASS = 0;
    public final static int ID = 1;
    public final static int SELECTION = 2;

    public Ruler(){

    }

    //全参构造器
    public Ruler(String url, String[] params, String[] values, String resultTagName, int type, int requestMoethod) {
        this.url = url;
        this.params = params;
        this.values = values;
        this.resultTagName = resultTagName;
        this.type = type;
        this.requestMoethod = requestMoethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getResultTagName() {
        return resultTagName;
    }

    public void setResultTagName(String resultTagName) {
        this.resultTagName = resultTagName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequestMoethod() {
        return requestMoethod;
    }

    public void setRequestMoethod(int requestMoethod) {
        this.requestMoethod = requestMoethod;
    }

}
