package com.example.demo.game;

public class YuanShen {
    static double a = 1853 / 807D;
    static double b = 0.57D;
    static double c = 0.79D;

    public static void main(String[] args) {
        //可莉
        calculate(new Role(1853,807,0.57,0.79));
        //皇女
        calculate(new Role(1434,607,0.48,1.26));
    }

    public static void calculate(Role r) {
        System.out.println("提升攻击力收益：" + 3 * (1 + r.getB() * r.getC()));
        System.out.println("提升暴击率收益：" + 2 * r.getA() * r.getC());
        System.out.println("提升暴击伤害收益：" + 4 * r.getA() * r.getB());
    }

    //角色
    static class Role {
        //攻击力百分比
        private double a = 0;
        //暴击率
        private double b = 0;
        //暴击伤害
        private double c = 0;

        public Role(double x, double y, double b, double c) {
            // a = 总攻击力/白字攻击力
            this.a = x / y;
            this.b = b;
            this.c = c;
        }

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double getB() {
            return b;
        }

        public void setB(double b) {
            this.b = b;
        }

        public double getC() {
            return c;
        }

        public void setC(double c) {
            this.c = c;
        }
    }
}
