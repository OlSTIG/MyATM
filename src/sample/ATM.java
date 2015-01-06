package sample;
public class ATM {
        
    //&#x41c;&#x43e;&#x436;&#x43d;&#x43e; &#x437;&#x430;&#x434;&#x430;&#x432;&#x430;&#x442;&#x44c; &#x43a;&#x43e;&#x43b;&#x438;&#x447;&#x435;&#x441;&#x442;&#x432;&#x43e; &#x434;&#x435;&#x43d;&#x435;&#x433; &#x432; &#x431;&#x430;&#x43d;&#x43a;&#x43e;&#x43c;&#x430;&#x442;&#x435;
    private double balance_ATM=10000;
    private Card card_in_ATM=null;
    ATM()
    {}
    ATM(double moneyInATM) throws NegativeAmount {
        if (moneyInATM>=0)
            balance_ATM=moneyInATM;
        else throw new NegativeAmount("ATM balance cannot be negative");
        
    }

    // Возвращает каоличестов денег в банкомате
    public double getMoneyInATM() {
        
        if (balance_ATM>=0) 
            return balance_ATM;
        else return -1;  
        }
    public void Push_in_card(Card card)
    {
       card_in_ATM=card;
    }
    //С вызова данного метода начинается работа с картой
    //Метод принимает карту и пин-код, проверяет пин-код карты и не заблокирована ли она
    //Если неправильный пин-код или карточка заблокирована, возвращаем false. При этом, вызов всех последующих методов у ATM с данной картой должен генерировать исключение NoCardInserted
    public boolean validateCard(Card card, int pinCode) throws NoCardInserted {
        try {
            if (card.isBlocked()==false && card.checkPin(pinCode)==true)
                return true;
            else
                return false;
        }
        catch (NullPointerException e)
        {
            throw new NoCardInserted("No card in ATM");
        }
    }
   
    //Возвращает сколько денег есть на счету
    public double checkBalance(int pinCode) throws NoCardInserted {
        
        if(validateCard(card_in_ATM,pinCode)==true)
            return card_in_ATM.getAccount().getBalance();
        else
            throw new NoCardInserted("No card in ATM");
        
    }
    
    //Метод для снятия указанной суммы
    //Метод возвращает сумму, которая у клиента осталась на счету после снятия
    //Кроме проверки счета, метод так же должен проверять достаточно ли денег в самом банкомате
    //Если недостаточно денег на счете, то должно генерироваться исключение NotEnoughMoneyInAccount 
    //Если недостаточно денег в банкомате, то должно генерироваться исключение NotEnoughMoneyInATM 
    //При успешном снятии денег, указанная сумма должна списываться со счета, и в банкомате должно уменьшаться количество денег
    public double getCash(double amount, int pinCode) throws NoCardInserted, NotEnoughMoneyInATM, NotEnoughMoneyInAccount {
        if(validateCard(card_in_ATM,pinCode)==true)
            if(balance_ATM>=amount)
            {
                if (card_in_ATM.getAccount().getBalance()>=amount)
                {
                balance_ATM=balance_ATM-amount;
                double t_amount=card_in_ATM.getAccount().getBalance();
                return t_amount-card_in_ATM.getAccount().withdrow(amount);
                }
                else throw new NotEnoughMoneyInAccount("Not enough money on your balance") ;
            }
            else throw new NotEnoughMoneyInATM("Not enough money in ATM") ;
        else
            {
              throw new NoCardInserted("No card in ATM");
            }
    }
}
