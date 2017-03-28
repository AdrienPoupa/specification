package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all Examining rooms
 */
public class ExaminingRoomContainer {
    private static List<ExaminingRoom> ExaminingRoomList  = new ArrayList<>();

    public static List<ExaminingRoom> getExaminingRoomList() {
        return ExaminingRoomList;
    }

    public static void setExaminingRoomList(List<ExaminingRoom> ExaminingRoomListArg) {
        ExaminingRoomList = ExaminingRoomListArg;
    }

    public static void add(ExaminingRoom e) {
        ExaminingRoomList.add(e);
    }
}
