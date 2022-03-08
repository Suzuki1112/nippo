package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.GoodConverter;
import actions.views.GoodView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Employee;
import models.Report;
import models.good;

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
    
    
    public good destroy(EmployeeView ev, ReportView rv) {
        List<good> goods =  em.createNamedQuery(JpaConst.Q_GOOD_GET_REPORT_AND_EMPLOYEE, good.class)
                .setParameter(JpaConst.GOOD_EMP, EmployeeConverter.toModel(ev))
                .setParameter(JpaConst.GOOD_REP, ReportConverter.toModel(rv))
                .getResultList();
        destroyInternal(goods.get(0));
             return null;
    }
    private void destroyInternal(good g) {
        em.getTransaction().begin();
        em.remove(g);       // データ削除
        em.getTransaction().commit();
    }
    /**
     * idを条件に取得したデータをEmployeeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public GoodView findOne(int id) {
        good g = findOneInternal(id);
        return GoodConverter.toView(g);
}
    private good findOneInternal(int id) {
        good g = em.find(good.class, id);

        return g;
}
    public long findgood(EmployeeView ev, ReportView rv) {
        long good = em.createNamedQuery(JpaConst.Q_GOOD_GET_REPORT_AND_EMPLOYEE_COUNT, Long.class)
                .setParameter(JpaConst.GOOD_EMP, EmployeeConverter.toModel(ev))
                .setParameter(JpaConst.GOOD_REP, ReportConverter.toModel(rv))
                .getSingleResult();
        return good;
    }
}
