/**
 * <P>工具程序包</P>
 * 
 * @version 1.0
 * @author LiuJian
 */
 
/**
 * <P>列赋值函数</P>
 * <P>给指定列赋值</P>
 * 
 * @param form
 * @param String：列名
 * @param String：数值字串
 * @return Boolean 成功 - true；失败 - false
 * 
 */
function setcolum(form,strcolum,strvalue) {
	var field=form[strcolum]
	if (field == null){ return false }
	if (field.length == null) {
		field.value = strvalue;
		return true;
	}
	for (var intFor = 0; intFor < field.length;intFor++) {
		field[intFor].value = strvalue;
	}
	return true;
}
 