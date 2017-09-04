package net.dagene.pmis.common.po;


public class javaEnum {
	/*11-翻译新建
	12-提交翻译审核
	13-翻译退回
	14-翻译审核通过
	15-提交JHH
	16-JHH提交回结果(翻译在此状态翻译)
	17-JHH退回
	18-翻译结果审核
	19-审核翻译
	21-结果发布*/
	public enum AuditStateEnum {
		state0( "0","未知"),
		state11("11","翻译新建"),
		state12("12","提交翻译审核"),
		state13("13","翻译退回"),
		state14("12","翻译审核通过"),
		state15("15","提交JHH"),
		state16("16","JHH提交回结果(翻译在此状态翻译)"),
		state17("17","JHH退回"),
		state18("18","翻译结果审核"),
		state19("19","审核翻译"),
		state21("21","结果发布");

	    private String name;  
	    private String value;  
		private AuditStateEnum(String name,String value){
			this.name=name;
			this.value=value;
		}
	    public String getName() {  
	        return this.name;  
	    }
	    public String getValue() {  
	        return this.value;  
	    }
	}
}
