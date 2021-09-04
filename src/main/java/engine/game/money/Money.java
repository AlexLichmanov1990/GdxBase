package engine.game.money;

public class Money {
    protected float money;
    protected float gold;

    protected MoneyListener listener;

    public Money() {
        money = 0;
        gold = 0;
    }

    public float getMoney() {
        return this.money;
    }

    public Money setMoney(float money) {
        this.money = money;
        if (listener != null) {
            listener.onChange();
        }
        return this;
    }

    public Money setMoney(Money money) {
        this.money = money.money;
        this.gold = money.gold;
        if (listener != null) {
            listener.onChange();
        }
        return this;
    }

    public float getGold() {
        return this.gold;
    }

    public Money setGold(int gold) {
        this.gold = gold;
        if (listener != null) {
            listener.onChange();
        }
        return this;
    }

    /**
     * @return true, если money >= this. Иначе возвращается false
     */
    public boolean checkMoney(Money money) {
        if (this.money < money.money) return false;
        if (this.gold < money.gold) return false;
        return true;
    }

    public Money addMoney(Money money) {
        this.money += money.money;
        this.gold += money.gold;
        if (listener != null) {
            listener.onChange();
        }
        return this;
    }

    public Money decMoney(Money money) {
        this.money -= money.money;
        this.gold -= money.gold;
        if (listener != null) {
            listener.onChange();
        }
        return this;
    }

    public void setListener(MoneyListener listener) {
        this.listener = listener;
    }

    public interface MoneyListener {
        void onChange();
    }
}
