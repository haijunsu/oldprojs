/**
 * <P>���߳����</P>
 * 
 * @version 1.0
 * @author LiuJian
 */
 
/**
 * <P>�и�ֵ����</P>
 * <P>��ָ���и�ֵ</P>
 * 
 * @param form
 * @param String������
 * @param String����ֵ�ִ�
 * @return Boolean �ɹ� - true��ʧ�� - false
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
 