package org.mybatis.generator.internal.types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.sql.Types;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:48 2019/1/16
 * @Modified By:
 */
public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {
    public MyJavaTypeResolverDefaultImpl() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
