package org.tutorial.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;
import org.tutorial.service.DeptService;
import org.tutorial.service.impl.DeptServiceImpl;

@WebServlet("/dept/dept.do")
public class DeptServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("listEmps_ByDeptno_A".equals(action) || "listEmps_ByDeptno_B".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                //1.接收請求參數
                Integer deptno = new Integer(req.getParameter("deptno"));

                //2.開始查詢資料
                DeptService deptSvc = new DeptServiceImpl();
                Set<EmpVO> set = deptSvc.getEmpsByDeptno(deptno);

                //3.查詢完成,準備轉交(Send the Success view)
                req.setAttribute("listEmps_ByDeptno", set); // 資料庫取出的set物件,存入request

                String url = null;
                if ("listEmps_ByDeptno_A".equals(action))
                    url = "/dept/listEmpsByDeptno.jsp"; // 成功轉交 dept/listEmpsByDeptno.jsp
                else if ("listEmps_ByDeptno_B".equals(action))
                    url = "/dept/listAll.jsp"; // 成功轉交 dept/listAll.jsp

                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                //其他可能的錯誤處理
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        if ("getOne_For_Update_Dept".equals(action)) {
            List<String> errorMsgs = new LinkedList<>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                //1.接收請求參數
                Integer deptno = new Integer(req.getParameter("deptno"));

                //2.開始查詢資料
                DeptServiceImpl deptService = new DeptServiceImpl();
                DeptVO deptVO = deptService.getOneDept(deptno);

                //3.查詢完成,準備轉交(Send the Success view)
                req.setAttribute("deptVO", deptVO); // 資料庫取出的empVO物件,存入req
                String url = "/dept/update.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update.jsp
                successView.forward(req, res);

                //其他可能的錯誤處理
            } catch (Exception e) {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/dept/listAll.jsp");
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                //1.接收請求參數 - 輸入格式的錯誤處理
                Integer deptno = new Integer(req.getParameter("deptno").trim());

                String dname = req.getParameter("dname");
                if (dname == null || dname.trim().length() == 0) {
                    errorMsgs.add("部門名稱: 請勿空白");
                }
                //以下練習正則(規)表示式(regular-expression)
                String dnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
                if (!dname.trim().matches(dnameReg)) {
                    errorMsgs.add("部門名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
                }

                String loc = req.getParameter("loc");
                if (loc == null || loc.trim().length() == 0) {
                    errorMsgs.add("部門基地: 請勿空白");
                }

                DeptVO deptVO = new DeptVO();
                deptVO.setDeptno(deptno);
                deptVO.setDname(dname);
                deptVO.setLoc(loc);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("deptVO", deptVO); // 含有輸入格式錯誤的deptVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/dept/update.jsp");
                    failureView.forward(req, res);
                    return;
                }

                //2.開始修改資料
                DeptServiceImpl deptSvc = new DeptServiceImpl();
                deptVO = deptSvc.update(deptno, dname, loc);

                //3.修改完成,準備轉交(Send the Success view)
                req.setAttribute("deptVO", deptVO); // 資料庫update成功後,正確的的deptVO物件,存入req
                String url = "/dept/listOne.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne.jsp
                successView.forward(req, res);

                //其他可能的錯誤處理
            } catch (Exception e) {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/dept/update.jsp");
                failureView.forward(req, res);
            }
        }

        if ("delete_Dept".equals(action)) {

            List<String> errorMsgs = new LinkedList<>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                //1.接收請求參數
                Integer deptno = new Integer(req.getParameter("deptno"));

                //2.開始刪除資料
                DeptService deptSvc = new DeptServiceImpl();
                deptSvc.deleteDept(deptno);

                //3.刪除完成,準備轉交(Send the Success view)
                String url = "/dept/listAll.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); //刪除成功後, 成功轉交 回到 /dept/listAll.jsp
                successView.forward(req, res);

                //其他可能的錯誤處理
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗: " + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/dept/listAll.jsp");
                failureView.forward(req, res);
            }
        }

    }
}