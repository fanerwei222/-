import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 敏感词过滤
 */
public class sensitiveWordFiltering {
    /**
     * 敏感词过滤方法
     * @param text
     * @return
     */
    public Set<String> sensitiveWordFiltering(String text)
    {
        // 初始化敏感词库对象
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        //这里加载敏感词列表，可以从数据库中获取或者从配置文件中获取
        List<SensitiveWord> words = new ArrayList<>();
        List<SensitiveWord> sensitiveWords = words;
        // 使用敏感词列表构建敏感词库
        Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        // 传入SensitivewordEngine类中的敏感词库
        SensitivewordEngine.sensitiveWordMap = sensitiveWordMap;
        // 得到敏感词有哪些，传入2表示获取所有敏感词
        Set<String> set = SensitivewordEngine.getSensitiveWord(text, 2);
        return set;
    }

}
