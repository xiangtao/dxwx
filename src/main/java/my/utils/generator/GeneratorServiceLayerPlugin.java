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
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 *@Description: service 层生成器
 */
public class GeneratorServiceLayerPlugin extends PluginAdapter
{
	private String serveicePackge = "";

	private String serveiceImplPackge;

	private String targetProject = "src";

	// --------------
	private String recordFullType = "";

	private String recordLowCaseFullType = "";

	private String mapperObjName = "";

	public boolean validate(List<String> warnings)
	{
		return true;
	}

	public GeneratorServiceLayerPlugin()
	{
		super();
		Properties pro = new Properties();
		InputStream inStream = GeneratorServiceLayerPlugin.class
				.getResourceAsStream("generatorConfig.properties");
		try
		{
			pro.load(inStream);
			targetProject = pro.getProperty("targetProject");
			serveicePackge = pro.getProperty("servicePackage");
			String impl = pro.getProperty("serviceImplPackage");
			serveiceImplPackge = serveicePackge + ".impl";
			if (impl != null)
			{
				serveiceImplPackge = impl;
			}
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
		// 生成Service接口 IxxxService
		recordFullType = introspectedTable.getBaseRecordType();
		int idx = recordFullType.lastIndexOf(".");
		if (idx != -1)
		{
			recordFullType = recordFullType.substring(idx + 1);
		}
		String serviceInterfaceFullName = serveicePackge + ".I" + recordFullType + "Service";
		recordLowCaseFullType = recordFullType.substring(0, 1).toLowerCase() + recordFullType.substring(1);
		mapperObjName = recordLowCaseFullType + "Mapper";
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(serviceInterfaceFullName);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		addAddMethod(interfaze, introspectedTable);
		addDeleteMethod(interfaze, introspectedTable);
		addUpdateMethod(interfaze, introspectedTable);
		addFindPaginationMethod(interfaze, introspectedTable);
		addFindByIdMethod(interfaze, introspectedTable);
		addFindAllMethod(interfaze, introspectedTable);
		addFindByCriteriaMethod(interfaze, introspectedTable);
		addGetExportDataMethod(interfaze, introspectedTable);
		addCountByExampleMethod(interfaze, introspectedTable);

		// 生成Service实现类
		String serviceImplFullName = serveiceImplPackge + "." + recordFullType + "ServiceImpl";
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(type);	// 导入接口
		type = new FullyQualifiedJavaType(serviceImplFullName);
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		type = new FullyQualifiedJavaType(serveicePackge.substring(0, serveicePackge.indexOf(".service")) + ".common.BusinessData");
		topLevelClass.setSuperClass(type);
		importedTypes.add(type);
		type = new FullyQualifiedJavaType(serviceInterfaceFullName);
		topLevelClass.addSuperInterface(type);
		topLevelClass.addAnnotation("@Service(\"" + recordLowCaseFullType + "Service\")");
		type = new FullyQualifiedJavaType("org.springframework.stereotype.Service");
		importedTypes.add(type);

		// add field, getter, setter for orderby clause
		// @Resource private UserMapper userMapper;
		type = new FullyQualifiedJavaType("javax.annotation.Resource");
		importedTypes.add(type);

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		String mapper = introspectedTable.getMyBatis3JavaMapperType();
		type = new FullyQualifiedJavaType(mapper);
		field.setType(type);
		idx = mapper.lastIndexOf(".");
		if (idx != -1)
		{
			mapper = mapper.substring(idx + 1);
		}
		mapper = mapper.substring(0, 1).toLowerCase() + mapper.substring(1);
		field.setName(mapper);
		field.addAnnotation("@Resource");
		topLevelClass.addField(field);
		importedTypes.add(type);
		topLevelClass.addImportedTypes(importedTypes);

		// add Method....
		addAddMethod(topLevelClass, introspectedTable);
		addDeleteMethod(topLevelClass, introspectedTable);
		addUpdateMethod(topLevelClass, introspectedTable);
		addFindPaginationMethod(topLevelClass, introspectedTable);
		addFindByIdMethod(topLevelClass, introspectedTable);
		addFindAllMethod(topLevelClass, introspectedTable);
		addFindByCriteriaMethod(topLevelClass, introspectedTable);
		addGetExportDataMethod(topLevelClass, introspectedTable);
		addCountByExampleMethod(topLevelClass, introspectedTable);

		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
		GeneratedJavaFile gjf = new GeneratedJavaFile(interfaze, targetProject);
		GeneratedJavaFile serviceImpl = new GeneratedJavaFile(topLevelClass, targetProject);
		answer.add(gjf);
		answer.add(serviceImpl);
		return answer;
	}

	private void addGetExportDataMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.LinkedHashMap");
		returnType.addTypeArgument(new FullyQualifiedJavaType("java.lang.String"));
		returnType.addTypeArgument(new FullyQualifiedJavaType("java.util.List"));
		importedTypes.add(returnType);
		Method method = new Method();
		method.addAnnotation("@Override");
		method.addSuppressTypeWarningsAnnotation();
		method.setReturnType(returnType);
		topLevelClass.addImportedTypes(importedTypes);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("getExportData");
		method.addBodyLine("LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();");
		method.addBodyLine("map.put(\"SheetName_1\", findAll());");
		method.addBodyLine("map.put(\"SheetName_2\", findAll());");
		method.addBodyLine("map.put(\"SheetName_3\", findAll());");
		method.addBodyLine("map.put(\"SheetName_N\", findAll());");
		method.addBodyLine("return map;");
		topLevelClass.addMethod(method);
	}
	
	private void addCountByExampleMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		importedTypes.add(type);

		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("countByExample");
		method.addParameter(new Parameter(type, "example"));
		method.addBodyLine("return " + mapperObjName + ".countByExample(example);");
		method.addAnnotation("@Override");
		
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}
	
	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addFindByCriteriaMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findByCriteria");
		FullyQualifiedJavaType pType = new FullyQualifiedJavaType(
				introspectedTable.getExampleType());
		importedTypes.add(pType);
		method.addParameter(new Parameter(pType, "criteria")); 
		method
				.addBodyLine("return " + mapperObjName + ".selectByExample(criteria);");
		method.addAnnotation("@Override");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addFindAllMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType
				.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable
				.getBaseRecordType()));
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findAll");
		method
				.addBodyLine("return " + mapperObjName + ".selectByExample(null);");
		method.addAnnotation("@Override");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addFindByIdMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findById");
		method.addParameter(new Parameter(
				new FullyQualifiedJavaType("java.lang.Long"), "id"));  
		// return userMapper.selectByPrimaryKey(id);
		method.addBodyLine("return " + mapperObjName + ".selectByPrimaryKey(id);");
		method.addAnnotation("@Override");
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addFindPaginationMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = FullyQualifiedJavaType
				.getNewMapInstance();
		type.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		type.addTypeArgument(FullyQualifiedJavaType.getObjectInstance());
		importedTypes.add(type);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(
				"my.utils.Pagination");
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findPagination");
		method.addParameter(new Parameter(type, "queryMap"));  
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "currentPage"));// int currentPage
		method.addParameter(new Parameter(FullyQualifiedJavaType .getIntInstance(), "pageSize"));// int pageSize
		method.addBodyLine("" + recordFullType + "Example " + recordLowCaseFullType + "Example = new " + recordFullType + "Example();");
		method.addBodyLine("//Criteria criteria = " + recordLowCaseFullType + "Example.createCriteria();");
		method.addBodyLine("// 设置搜索条件参数");
		method.addBodyLine("//if(queryMap != null){");
		method.addBodyLine("//if(queryMap.containsKey(\"username\")) {");
		method.addBodyLine("//criteria.andUserNameLike(\"%\"+(String)queryMap.get(\"username\")+\"%\");");
		method.addBodyLine("//}");
		method.addBodyLine("//if(queryMap.containsKey(\"email\")){");
		method.addBodyLine("//criteria.andEmailLike((String)queryMap.get(\"email\"));");
		method.addBodyLine("//}");
		method.addBodyLine("//}");
		method.addBodyLine("// 设置分页参数");
		method.addBodyLine(recordLowCaseFullType + "Example.setPageSize(pageSize);");
		method.addBodyLine(recordLowCaseFullType + "Example.setStartIndex((currentPage-1)*pageSize);");
		method.addBodyLine("List<" + recordFullType + "> items = " + mapperObjName + ".selectByExample(" + recordLowCaseFullType + "Example);");
		method.addBodyLine("int totalCount = (int)" + mapperObjName + ".countByExample(" + recordLowCaseFullType + "Example);");
		method.addBodyLine("return new Pagination(pageSize, currentPage, totalCount, items);");
		method.addAnnotation("@Override");

		// import com.tyz.axj.persistent.pojo.admin.ObjectCriteria.Criteria;
		FullyQualifiedJavaType pType = new FullyQualifiedJavaType(
				introspectedTable.getExampleType() + ".Criteria");
		importedTypes.add(pType);

		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method);
	}

	/**
	 * @Description:public void update(User entity)
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addUpdateMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(type);

		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("update");
		method.addParameter(new Parameter(type, "entity"));
		method.addBodyLine("return " + mapperObjName + ".updateByPrimaryKeySelective(entity);");
		method.addAnnotation("@Override");
		topLevelClass.addMethod(method);
		topLevelClass.addImportedTypes(importedTypes);

	}

	/**
	 * @Description:public void delete(User entity){
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addDeleteMethod(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getExampleType());
		importedTypes.add(type);

		Method method1 = new Method();
		method1.setReturnType(new FullyQualifiedJavaType("int"));
		method1.setVisibility(JavaVisibility.PUBLIC);
		method1.setName("delete");
		method1.addParameter(new Parameter(type, "example"));
		method1.addBodyLine("return " + mapperObjName + ".deleteByExample(example);");
		method1.addAnnotation("@Override");
		
		Method method2 = new Method();
		method2.setReturnType(new FullyQualifiedJavaType("int"));
		method2.setVisibility(JavaVisibility.PUBLIC);
		method2.setName("delete");
		method2.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id"));
		method2.addBodyLine("return " + mapperObjName + ".deleteByPrimaryKey(id);");
		method2.addAnnotation("@Override");
		
		topLevelClass.addImportedTypes(importedTypes);
		topLevelClass.addMethod(method1);
		topLevelClass.addMethod(method2);
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
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(type);

		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("add");
		method.addParameter(new Parameter(type, "entity"));
		method.addBodyLine("return " + mapperObjName + ".insert(entity);");
		method.addAnnotation("@Override");
		topLevelClass.addMethod(method);
		topLevelClass.addImportedTypes(importedTypes);

	}

	protected void addAddMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(type);
		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("add");
		method.addParameter(new Parameter(type, "entity"));  
		method.addJavaDocLine("/**\n" + "* @Description: 添加实体\n" + "* @param entity\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	protected void addUpdateMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(type);
		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("update");
		method.addParameter(new Parameter(type, "entity"));  
		method.addJavaDocLine("/**\n" + "* @Description: 更新实体\n" + "* @param entity\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	protected void addDeleteMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getExampleType());
		importedTypes.add(type);
		Method method1 = new Method();
		method1.setReturnType(new FullyQualifiedJavaType("int"));
		method1.setVisibility(JavaVisibility.PUBLIC);
		method1.setName("delete");
		method1.addParameter(new Parameter(type, "example"));  
		method1.addJavaDocLine("/**\n" + "* @Description: 根据条件删除\n" + "* @param example\n" + "*/");

		Method method2 = new Method();
		method2.setReturnType(new FullyQualifiedJavaType("int"));
		method2.setVisibility(JavaVisibility.PUBLIC);
		method2.setName("delete");
		method2.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id"));  
		method2.addJavaDocLine("/**\n" + "* @Description: 根据主键删除\n" + "* @param id\n" + "*/");

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method1);
		interfaze.addMethod(method2);
	}

	// Pagination findPagination(Map<String,Object> queryMap,int currentPage,int pageSize);
	protected void addFindPaginationMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = FullyQualifiedJavaType
				.getNewMapInstance();
		type.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		type.addTypeArgument(FullyQualifiedJavaType.getObjectInstance());
		importedTypes.add(type);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("my.utils.Pagination");
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findPagination");
		method.addParameter(new Parameter(type, "queryMap"));  
		method.addParameter(new Parameter(FullyQualifiedJavaType
				.getIntInstance(), "currentPage"));// int currentPage
		method.addParameter(new Parameter(FullyQualifiedJavaType
				.getIntInstance(), "pageSize"));// int pageSize
		method.addJavaDocLine("/**\n" + "* @Description: 根据参数集按页查询\n" + "* @param queryMap 查询参数\n" 
				+ "* @param currentPage 当前页\n" + "* @param pageSize 每页大小\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	protected void addFindByIdMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(
				introspectedTable.getBaseRecordType());
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findById");
		method.addParameter(new Parameter(
				new FullyQualifiedJavaType("java.lang.Long"), "id"));  
		method.addJavaDocLine("/**\n" + "* @Description: 根据主键查询\n" + "* @param id\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	protected void addFindAllMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType
				.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable
				.getBaseRecordType()));
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findAll");
		method.addJavaDocLine("/**\n" + "* @Description: 查询所有数据\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	protected void addFindByCriteriaMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType
				.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable
				.getBaseRecordType()));
		importedTypes.add(returnType);
		Method method = new Method();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("findByCriteria");
		FullyQualifiedJavaType pType = new FullyQualifiedJavaType(
				introspectedTable.getExampleType());
		importedTypes.add(pType);
		method.addParameter(new Parameter(pType, "criteria")); 
		method.addJavaDocLine("/**\n" + "* @Description: 根据条件查询\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}
	
	protected void addGetExportDataMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.LinkedHashMap");
		returnType.addTypeArgument(new FullyQualifiedJavaType("java.lang.String"));
		returnType.addTypeArgument(new FullyQualifiedJavaType("java.util.List"));
		importedTypes.add(returnType);
		Method method = new Method();
		method.addSuppressTypeWarningsAnnotation();
		method.setReturnType(returnType);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("getExportData");
		method.addJavaDocLine("/**\n" + "* @Description: 获取导出到Excel的数据\n" + "*/");
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}
	
	protected void addCountByExampleMethod(Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		importedTypes.add(type);
		
		Method method = new Method();
		method.setReturnType(new FullyQualifiedJavaType("int"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("countByExample");
		method.addParameter(new Parameter(type, "example"));
		method.addJavaDocLine("/**\n" + "* @Description: 根据条件查询数量\n" + "*/");

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}
}