package my.utils.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 *@Description: Action 层生成器
 */
public class GeneratorActionLayerPlugin extends PluginAdapter
{
	private String actionPackge = "";

	private String serveicePackge = "";

	private String targetProject = "src";

	// --------------
	private String recordLowerFullType = "";

	private String serviceObjName = "";

	@Override
	public boolean validate(List<String> warnings)
	{
		return true;
	}

	public GeneratorActionLayerPlugin()
	{
		super();
		Properties pro = new Properties();
		InputStream inStream = GeneratorActionLayerPlugin.class
				.getResourceAsStream("generatorConfig.properties");
		try
		{
			pro.load(inStream);
			targetProject = pro.getProperty("targetProject");
			actionPackge = pro.getProperty("actionPackage");
			serveicePackge = pro.getProperty("servicePackage");
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
			IntrospectedTable introspectedTable)
	{
		// 生成Action UserAction
		String recordFullType = introspectedTable.getBaseRecordType();
		int idx = recordFullType.lastIndexOf(".");
		if (idx != -1)
		{
			recordFullType = recordFullType.substring(idx + 1);
		}
		recordLowerFullType = recordFullType.substring(0, 1).toLowerCase()
				+ recordFullType.substring(1);
		serviceObjName = recordLowerFullType + "Service";

		String actionFullName = actionPackge + "." + recordFullType + "Action";
		String serviceInterfaceFullName = serveicePackge + ".I"
				+ recordFullType + "Service";

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		// 导入 com.tyz.axj.utils.DwzAjaxJsonUtil;
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				"com.tyz.axj.utils.DwzAjaxJsonUtil");
		importedTypes.add(type);
		type = FullyQualifiedJavaType.getNewMapInstance();
		importedTypes.add(type);
		// import ...
		type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(type);

		type = new FullyQualifiedJavaType(actionFullName);
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		type = new FullyQualifiedJavaType("com.tyz.axj.web.action.BackendBaseAction");
		topLevelClass.setSuperClass(type);
		importedTypes.add(type);
		// add field, getter, setter for orderby clause
		// @Resource
		// private IUserService userService;
		type = new FullyQualifiedJavaType("javax.annotation.Resource");
		importedTypes.add(type);
		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		type = new FullyQualifiedJavaType(serviceInterfaceFullName);
		importedTypes.add(type);
		field.setType(type);
		field.setName(serviceObjName); //$NON-NLS-1$
		field.addAnnotation("@Resource");
		topLevelClass.addField(field);

		// private Pagination page;
		field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		type = new FullyQualifiedJavaType("com.tyz.axj.utils.Pagination");
		importedTypes.add(type);
		field.setType(type);
		field.setName("page");
		topLevelClass.addField(field);

		field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		type = new FullyQualifiedJavaType(recordFullType);
		field.setType(type);
		field.setName(recordLowerFullType);
		topLevelClass.addField(field);
		topLevelClass.addImportedTypes(importedTypes);
		// setter getter
		addSetPageMethod(topLevelClass, introspectedTable);
		addGetPageMethod(topLevelClass, introspectedTable);
		addGetBaseRecordMethod(topLevelClass, introspectedTable,
				recordFullType, recordLowerFullType);
		addSetBaseRecordMethod(topLevelClass, introspectedTable,
				recordFullType, recordLowerFullType);

		// add Method....
		addShowIndexMethod(topLevelClass, introspectedTable);
		addShowAddMethod(topLevelClass, introspectedTable);
		addAddMethod(topLevelClass, introspectedTable);
		addDeleteMethod(topLevelClass, introspectedTable);
		addShowUpdateMethod(topLevelClass, introspectedTable);
		addUpdateMethod(topLevelClass, introspectedTable);

		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
		GeneratedJavaFile action = new GeneratedJavaFile(topLevelClass,
				targetProject);
		answer.add(action);
		return answer;
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param recordLowerFullType
	 * @param recordLowerFullType2
	 */
	private void addGetBaseRecordMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String recordFullType,
			String recordLowerFullType)
	{
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType(introspectedTable
				.getBaseRecordType()));
		method.setName("get" + recordFullType); //$NON-NLS-1$
		method.addBodyLine("return this." + recordLowerFullType + ";"); //$NON-NLS-1$
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param recordLowerFullType
	 * @param recordLowerFullType2
	 */
	private void addSetBaseRecordMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String recordFullType,
			String recordLowerFullType)
	{
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + recordFullType); //$NON-NLS-1$
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				recordFullType), recordLowerFullType));
		method
				.addBodyLine("this." + recordLowerFullType + " = " + recordLowerFullType + ";"); //$NON-NLS-1$
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addGetPageMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType(
				"com.tyz.axj.utils.Pagination"));
		method.setName("getPage"); //$NON-NLS-1$
		method.addBodyLine("return this.page;"); //$NON-NLS-1$
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addSetPageMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("setPage"); //$NON-NLS-1$
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				"com.tyz.axj.utils.Pagination"), "page"));
		method.addBodyLine("this.page = page;"); //$NON-NLS-1$
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description: public String showIndex()
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addShowIndexMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = FullyQualifiedJavaType
				.getStringInstance();
		importedTypes.add(type);
		Method method = new Method();
		method.setReturnType(type);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("showIndex");

		method
				.addBodyLine("Map<String,Object> params = super.getRequestParameters(request);");
		method.addBodyLine("int pageNum = Pagination.CURRENTPAGE;");
		method.addBodyLine("int pageSize = Pagination.PAGESIZE;");
		method.addBodyLine("if(params.containsKey(\"pageNum\")){");
		method
				.addBodyLine("pageNum = Integer.parseInt((String)params.get(\"pageNum\"));");
		method.addBodyLine("}");
		method.addBodyLine("if(params.containsKey(\"numPerPage\")){");
		method
				.addBodyLine("pageSize = Integer.parseInt((String)params.get(\"numPerPage\"));");
		method.addBodyLine("}");
		method.addBodyLine("page = " + serviceObjName
				+ ".findPagination(params, pageNum, pageSize);");
		method.addBodyLine("return \"showIndex\";");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:public void delete() throws IOException
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addDeleteMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("delete");
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				"java.io.IOException");
		importedTypes.add(type);
		method.addException(type);

		method
				.addBodyLine("Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_NAVTABID, navTabId);");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_CALLBACKTYPE, \"\");");
		method.addBodyLine("if(" + recordLowerFullType + " == null){");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_MESSAGE, \"操作失败！\");");
		method.addBodyLine("}else{");
		method.addBodyLine(serviceObjName + ".delete(" + recordLowerFullType
				+ ");");
		method.addBodyLine("}");
		method.addBodyLine("super.writeMap(json);");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:public String showUpdate()
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addShowUpdateMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = FullyQualifiedJavaType
				.getStringInstance();
		importedTypes.add(type);
		Method method = new Method();
		method.setReturnType(type);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("showUpdate");

		method.addBodyLine(recordLowerFullType + " = " + serviceObjName
				+ ".findById(" + recordLowerFullType + ".getId());");
		method.addBodyLine("return \"showUpdate\";");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:public void update(Object entity)
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addUpdateMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("update");

		method
				.addBodyLine("Map<String,Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_NAVTABID, navTabId);");
		method.addBodyLine("if(" + recordLowerFullType + " == null){");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_MESSAGE, \"操作失败！\");");
		method.addBodyLine("}else{");
		method.addBodyLine(serviceObjName + ".update(" + recordLowerFullType
				+ ");");
		method.addBodyLine("}");
		method.addBodyLine("super.writeMap(json);");
		method.addException(new FullyQualifiedJavaType("java.io.IOException"));
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:public void delete(Object entity){
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addShowAddMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = FullyQualifiedJavaType
				.getStringInstance();
		importedTypes.add(type);
		Method method = new Method();
		method.setReturnType(type);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("showAdd");
		method.addBodyLine("return \"showAdd\";");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description: public void add(Object entity){
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addAddMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("add");
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				"java.io.IOException");
		importedTypes.add(type);
		method.addException(type);

		method
				.addBodyLine("Map<String,Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_NAVTABID, navTabId);");
		method.addBodyLine("if(" + recordLowerFullType + " == null){");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);");
		method.addBodyLine("json.put(DwzAjaxJsonUtil.KEY_MESSAGE, \"操作失败！\");");
		method.addBodyLine("}else{");
		method.addBodyLine(serviceObjName + ".add(" + recordLowerFullType
				+ ");");
		method.addBodyLine("}");
		method.addBodyLine("super.writeMap(json);");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}
}