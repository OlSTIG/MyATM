/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author STIG
 */
class NoCardInserted extends Exception {
    NoCardInserted(String msg) {
        super(msg);
    }

}
class NotEnoughMoneyInAccount extends Exception {
    NotEnoughMoneyInAccount(String msg) {
        super(msg);
    }
}
class NotEnoughMoneyInATM extends Exception {
    NotEnoughMoneyInATM(String msg) {
        super(msg);
    }
}

class NegativeAmount extends Exception {
    NegativeAmount(String msg) {
        super(msg);
    }
}
public class Exception_ATM {
    
}
