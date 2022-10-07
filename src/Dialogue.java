public class Dialogue { public static void main(String[] args) {
//创建Communication对象
    Communication student1 = new Communication();
//实现Runnable接口
    User a_speaker = new User(student1);
    User b_speaker = new User(student1);
//创建多线程对象
    Thread User1 = new Thread(a_speaker, "用户一");
    Thread User2 = new Thread(b_speaker, "用户二");
    User2.start();
    User1.start();
}
}
class Communication {
    public static int i = 0;

    public static void chating1(Communication a) {
        synchronized (a) {
            while (true) {
                if (i % 2 == 0) {
                    while (i <= 8) {
                        if (i == 0) {
                            System.out.println(Thread.currentThread().getName() + ":你觉得我们学校好吃的有什么呀");
                        } else if (i == 2) {
                            System.out.println(Thread.currentThread().getName() + ":是的是的，我也觉得那里的麻辣烫很好吃");
                        } else if (i == 4) {
                            System.out.println(Thread.currentThread().getName() + ":有一说一，我比较喜欢阳光食堂的拼菜，哈哈哈主要是便宜");
                        } else if (i == 6) {
                            System.out.println(Thread.currentThread().getName() + ":自从我来到学校之后，我都没吃过风华的烧烤");
                        } else if (i == 8) {
                            System.out.println(Thread.currentThread().getName() + ":好好好，我可记下来了哈，一言为定啦");
                        }
                        i++;
                        a.notify();
                        break;
                    }
                } else {
                    try {
                        a.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    public static void chating2(Communication a) {
        synchronized (a) {
            while (true) {
                if (i % 2 != 0) {
                    while (i <= 9) {
                        if (i == 1) {
                            System.out.println(Thread.currentThread().getName() + ":我觉得桂苑二楼的麻辣烫比较好吃");
                        } else if (i == 3) {
                            System.out.println(Thread.currentThread().getName() + ":那你最喜欢学校哪个食堂的饭菜呢");
                        } else if (i == 5) {
                            System.out.println(Thread.currentThread().getName() + ":我也觉得比较便宜，不过预算充足的话也可以吃风华的烧烤");
                        } else if (i == 7) {
                            System.out.println(Thread.currentThread().getName() + ":我下次可以请你吃");
                        } else if (i == 9) {
                            System.out.println(Thread.currentThread().getName() + ":一言为定");
                        }
                        i++;
                        a.notify();
                        break;
                    }
                } else {
                    try {
                        a.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
class User implements Runnable {
    Communication communicating;

    public User(Communication communicating) {
        this.communicating = communicating;
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("用户一")) {
            new Communication().chating1(this.communicating);
        }
        if (Thread.currentThread().getName().equals("用户二")) {
            new Communication().chating2(this.communicating);
        }
    }
}
