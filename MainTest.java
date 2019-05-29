import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class MainTest {
    public static void main(String[] args){
        System.out.println("**************************------------**************************");
        /**
         * 组装测试数据
         */
        List<SensitiveWord> words = new ArrayList<>();
        SensitiveWord item = new SensitiveWord();
        item.setContent("中国人民");
        words.add(item);
        item = new SensitiveWord();
        item.setContent("中国男人");
        words.add(item);
        item = new SensitiveWord();
        item.setContent("日本人");
        words.add(item);
        item = new SensitiveWord();
        item.setContent("日本鬼子");
        words.add(item);
        item = new SensitiveWord();
        item.setContent("日本鬼子");
        words.add(item);
        item = new SensitiveWord();
        item.setContent("五星红旗");
        words.add(item);
        SensitiveWordInit wordInit = new SensitiveWordInit();
        Map sensitiveWordMap = wordInit.initKeyWord(words);
        // 传入SensitivewordEngine类中的敏感词库
        SensitivewordEngine.sensitiveWordMap = sensitiveWordMap;
        // 得到敏感词有哪些，传入2表示获取所有敏感词
        Set<String> set = SensitivewordEngine.getSensitiveWord("国日本鬼子日本人", 2);

        System.out.println(set.size());
        System.out.println("**************************------------**************************");

    }
}
