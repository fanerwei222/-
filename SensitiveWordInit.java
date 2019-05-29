import java.util.*;
/**
 * 敏感词库初始化
 *
 * @author AlanLee
 *
 */
public class SensitiveWordInit
{
    /**
     * 敏感词库
     */
    public HashMap sensitiveWordBoxMap;

    /**
     * 初始化敏感词列表,将敏感词列表中的敏感词内容组装到set集合当中
     *
     * @return
     */
    public Map initKeyWord(List<SensitiveWord> sensitiveWords)
    {
        try
        {
            // 从敏感词集合对象中取出敏感词并封装到Set集合中
            Set<String> keyWordSet = new HashSet<String>();
            for (SensitiveWord s : sensitiveWords)
            {
                keyWordSet.add(s.getContent().trim());
            }
            // 传入敏感词集合用以组装敏感词库
            addSensitiveWordToBoxHashMap(keyWordSet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sensitiveWordBoxMap;
    }

    /**
     * 封装敏感词库(组装敏感词到敏感词库)
     *
     * @param keyWordSet
     */
    private void addSensitiveWordToBoxHashMap(Set<String> keyWordSet)
    {
        // 初始化敏感词库并且设置大小为敏感词列表大小
        sensitiveWordBoxMap = new HashMap(keyWordSet.size());
        // 敏感词的全部字符
        String key = null;
        // (临时map)保存敏感词库的地址引用或者保存辅助map的地址引用的一个map对象，可以认为是封装过程中当前的map对象。
        Map boxMapOrAuxiMap = null;
        // 用来辅助构建敏感词库，主要功能是用来区分是否是敏感词的最后一位
        Map<String, String> AuxiMap = null;
        // 使用一个迭代器来循环敏感词集合
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext())
        {
            //当前循环的敏感词
            key = iterator.next();
            // 将临时map指向敏感词库map的引用地址，这里临时map等于敏感词库，HashMap对象在内存中占用的是同一个地址，所以此boxMapOrAuxiMap对象的变化，sensitiveWordMap对象也会跟着改变
            boxMapOrAuxiMap = sensitiveWordBoxMap;
            for (int i = 0; i < key.length(); i++)
            {
                // 截取敏感词当中的字，把敏感词库中的字设为HashMap对象的Key键值
                char keyChar = key.charAt(i);

                // 判断这个字是否存在于敏感词库中
                Object wordMap = boxMapOrAuxiMap.get(keyChar);
                if (wordMap != null)
                {
                    //如果这个字存在于敏感词库中，那么就可以直接取该字为key的对应的map对象，并且开始下一个字的匹配；
                    // 例如：日本人，日本鬼子；如果日本人已经在敏感词库中，且当前要操作的的敏感词是日本鬼子，那么可以直接取以“日”为key的map对象，并且直接进行下一步操作
                    boxMapOrAuxiMap = (Map) wordMap;
                }
                else
                {
                    //当前字不存在于敏感词库中，那么新建一个未结束的标识符的map，并且将该map指向临时map的引用，直接进行下一个字匹配。
                    AuxiMap = new HashMap<String, String>();
                    AuxiMap.put("isEnd", "0");
                    boxMapOrAuxiMap.put(keyChar, AuxiMap);
                    boxMapOrAuxiMap = AuxiMap;
                }

                // 如果该字是当前敏感词的最后一个字，则标识为结尾字
                if (i == key.length() - 1)
                {
                    boxMapOrAuxiMap.put("isEnd", "1");
                }
                System.out.println("封装敏感词库过程："+ sensitiveWordBoxMap);
            }
            System.out.println("查看敏感词库数据:" + sensitiveWordBoxMap);
        }
    }
}