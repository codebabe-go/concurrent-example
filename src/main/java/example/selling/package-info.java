/**
 * author: code.babe
 * date: 2016-09-14 10:53
 * 顾客买票的高并发场景
 * 库存唯一, 许多顾客同时在不同的窗口买票
 * customer(n) - office(n) - pool(1) 这样的线程模型开始建模
 * 这里涉及到先锁定然后付款这个顺序, 一旦锁定其他人就无法获取到这个座位
 */
package example.selling;