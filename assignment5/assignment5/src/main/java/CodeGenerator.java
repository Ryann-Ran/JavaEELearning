import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
/**
 * @author Ryann
 * @create 2023/10/21 - 14:49
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //配置数据源
        autoGenerator.setDataSource(dataSourceConfig());
        //全局配置
        autoGenerator.setGlobalConfig(globalConfig());
        //配置包名
        autoGenerator.setPackageInfo(packageConfig());
        //配置生成策略
        autoGenerator.setStrategy(strategyConfig());
        //执行生成操作
        autoGenerator.execute();
    }

    private static StrategyConfig strategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置当前参与生成的表名，参数为可 变参数
        strategyConfig.setInclude("product","supplier");
        //设置数据库表的前缀名称，模块名 = 数据库表名 - 前缀名 例如： User = tbl_user - tbl_
        //strategyConfig.setTablePrefix("tbl_");
        //设置是否启用Rest风格
        strategyConfig.setRestControllerStyle(true);
        // 设置乐观锁字段名
        //strategyConfig.setVersionFieldName("version");
        // 设置逻辑删除字段名
        //strategyConfig.setLogicDeleteFieldName("deleted");
        // 设置是否启用lombok
        strategyConfig.setEntityLombokModel(true);
        return strategyConfig;
    }

    private static PackageConfig packageConfig() {
        PackageConfig packageInfo = new PackageConfig();
        //设置生成的包名，与代码所在位置不冲突， 二者叠加组成完整路径
        packageInfo.setParent("edu.whu.assignment5");
        //设置实体类包名
        packageInfo.setEntity("domain");
        //设置数据层包名
        packageInfo.setMapper("dao");
        return packageInfo;
    }

    private static GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        //设置代码生成位置
        globalConfig.setOutputDir(System.getProperty("user.dir")  // System.getProperty(“user.dir”)返回的是执行运行Java程序命令的路径。此时即项目顶端目录
                +"/src/main/java");
        //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setOpen(false);
        //设置作者
        globalConfig.setAuthor("Yuran Wang");
        //设置是否覆盖原始生成的文件
        globalConfig.setFileOverride(true);
        //设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setMapperName("%sDao");
        //设置Id生成策略
        globalConfig.setIdType(IdType.AUTO);
        return globalConfig;
    }

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/assignment5?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("030829");
        return dataSource;
    }
}
