package org.fisco.bcos.contract;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

// TODO
public class User {
    private final String address = "1234567890";

    protected User(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static User load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new User(contractAddress, web3j, credentials, contractGasProvider);
    }

    public BigInteger register(String _id, String _pwd) {
        if (_id.equals("exist"))
            return new BigInteger("-1");
        else
            return new BigInteger("0");
    }

    public Tuple2<BigInteger, String> login(String _id, String _pwd) {

        if (_id.equals("not exist"))
            return new Tuple2<>(new BigInteger("1"), address);
        else
            return new Tuple2<>(new BigInteger("0"), address);
    }

    public BigInteger getBalance(String _id) throws Exception {
        if (_id.equals("not exist"))
            throw new Exception("account " + _id + " not exist");
        return new BigInteger("123456789");
    }

    public Boolean setBalance(String _id, BigInteger balance) {
        if (_id.equals("not exist"))
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    public Boolean isAdmin(String _id) {
        if (_id.equals("admin"))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
