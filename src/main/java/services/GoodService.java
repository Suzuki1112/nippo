package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.GoodConverter;
import actions.views.GoodView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import models.Employee;
import models.Report;

public class GoodService extends ServiceBase {

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Report findOneInternalrep(int rep) {
        return em.find(Report.class, rep);
    }
    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ReportView findOneRep(int rep) {
        return ReportConverter.toView(findOneInternalrep(rep));
    } 
    /**
     * idを条件に取得したデータをEmployeeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public EmployeeView findOneEmp(int emp) {
        Employee e = findOneInternalemp(emp);
        return EmployeeConverter.toView(e);
    }
    private Employee findOneInternalemp(int emp) {
        Employee e = em.find(Employee.class, emp);

        return e;
    }
    
    
    public List<String> create(GoodView gv) {
            createInternal(gv);
            return null;
        }

    
    private void createInternal(GoodView gv) {

        em.getTransaction().begin();
        em.persist(GoodConverter.toModel(gv));
        em.getTransaction().commit();

    }
    
    public List<String> destroy(GoodView gv) {
             destroyInternal(gv);
             return null;
    }
    private void destroyInternal(GoodView gv) {
        em.getTransaction().begin();
        em.remove(GoodConverter.toModel(gv));       // データ削除
        em.getTransaction().commit();
    }
}
