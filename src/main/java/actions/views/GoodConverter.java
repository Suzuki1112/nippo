package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.good;

/**
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class GoodConverter {

    /**
     * GoodモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param gv GoodViewのインスタンス
     * @return goodのインスタンス
     */
    public static good toModel(GoodView gv) {
        return new good(
                gv.getId(),
                EmployeeConverter.toModel(gv.getEmployee()),
                ReportConverter.toModel(gv.getReport())
                
                
                );
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param g goodのインスタンス
     * @return GoodViewのインスタンス
     */
    public static  GoodView toView(good g) {

        if (g == null) {
            return null;
        }

        return new GoodView(
                g.getId(),
                EmployeeConverter.toView(g.getEmployee()),
                ReportConverter.toView(g.getReport())
                
                );

    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<GoodView> toViewList(List<good> list) {
        List<GoodView> evs = new ArrayList<>();

        for (good g : list) {
            evs.add(toView(g));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param g DTOモデル(コピー先)
     * @param gv Viewモデル(コピー元)
     */
    public static void copyViewToModel(good g, GoodView gv) {
        g.setId(gv.getId());
        g.setEmployee(EmployeeConverter.toModel(gv.getEmployee()));
        g.setReport(ReportConverter.toModel(gv.getReport()));

    }

}