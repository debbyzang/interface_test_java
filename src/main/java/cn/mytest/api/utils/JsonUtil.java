package cn.mytest.api.utils;

import com.google.gson.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonUtil {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> DateUtil.parse(json.getAsString()))
            .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, typeOfSrc, context) -> new JsonPrimitive(DateUtil.format(date)))
            .disableHtmlEscaping().setPrettyPrinting().create();

    public static String toJson(Object object){
        return GSON.toJson(object);
    }

    public static class DateUtil{

        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        static String format(Date date){
            if (date == null) {
                return null;
            }
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(formatter);
        }

        static Date parse(String dateStr){
            Instant instant = LocalDateTime.parse(dateStr, formatter).atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }

        public static void main(String[] args){
            for (int i = 0; i < 1000; i++) {
                new Thread(() -> {
                    String str = format(new Date());
                    System.out.println(str);
                    System.out.println(parse(str));
                }).start();
            }

        }
    }

    /**
     * 格式化
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\'){
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }


}
