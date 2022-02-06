package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.GoodView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.GoodService;

public class GoodAction extends ActionBase {

    private GoodService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new GoodService();

        //メソッドを実行
        invoke();
        service.close();
    }
    /**
     * reportのIDから日報データを取得
     * @throws ServletException
     * @throws IOException
     */
    public void GoodCreate() throws ServletException, IOException {

      //idを条件に日報データを取得する
        ReportView rv = service.findOneRep(toNumber(getRequestParam(AttributeConst.REP_ID)));

      //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        
        
        //パラメータの値を元に従業員情報のインスタンスを作成する
        GoodView gv = new GoodView(
                null,
                ev,
                rv);
      //情報登録
        service.create(gv);
        
        putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ
        //show画面にリダイレクト
        forward(ForwardConst.FW_REP_SHOW);

    }
}
