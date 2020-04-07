package com.us.srs.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.us.srs.MyApplication;
import com.us.srs.db.bean.FreeFlowBean;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;
import com.us.srs.db.bean.User;

import java.util.*;

public class LiteDbUtils<T> {
    static LiteOrm liteOrm = MyApplication.liteOrm;

    public static void insertUser(User user) {
        liteOrm.insert(user, ConflictAlgorithm.Abort);
    }

    public static void insertUser(FreeFlowBean flowBean) {
        liteOrm.insert(flowBean, ConflictAlgorithm.Abort);
    }

    public static void insertItem(TransactionItemBean itemBean) {
        liteOrm.insert(itemBean, ConflictAlgorithm.Abort);
    }

    public static void insertItem(TransactionItemSellOutBean itemBean) {
        liteOrm.insert(itemBean, ConflictAlgorithm.Abort);
    }

    public static boolean canUserLogin(String phone, String pwd) {
        List<User> userList = getQueryByWhere(User.class, "username", new String[]{phone});
        if (userList != null && userList.size() > 0) {
            if (userList.get(0).getPassWord().equals(pwd)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean canRegister(String phone) {
        List<User> userList = getQueryByWhere(User.class, "username", new String[]{phone});
        if (userList != null && userList.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean updatePwd(String phone, String pwd) {
        List<User> userList = getQueryByWhere(User.class, "username", new String[]{phone});
        if (userList != null && userList.size() > 0) {
            User user = userList.get(0);
            user.setPassWord(pwd);
            liteOrm.update(user);
            return true;
        } else {
            return false;
        }
    }

    public static User getFindUserByPhone(String phone) {
        List<User> userList = getQueryByWhere(User.class, "username", new String[]{phone});
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }

    }

    public static <T> List<T> getQueryByWhere(Class<T> cla, String field, Object[] value) {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    public static <T> List<T> getQuery(Class<T> cla) {
        return liteOrm.<T>query(new QueryBuilder(cla));
    }

    public static void deletTransactionItemBean(TransactionItemBean transactionItemBean) {
        liteOrm.delete(transactionItemBean);
    }

    public static void deletTransactionItemBean(TransactionItemSellOutBean transactionItemBean) {
        liteOrm.delete(transactionItemBean);
    }

    public static void updateFreeFlow(FreeFlowBean flowBean) {
        liteOrm.update(flowBean);
    }
}
