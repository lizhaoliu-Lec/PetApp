package org.fisco.bcos.contract;

import java.math.BigInteger;
import java.util.Arrays;

import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class StringProcess extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506109d0806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a12983681461005c578063b23983d914610123578063ff74927b146101c9575b600080fd5b34801561006857600080fd5b50610109600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506102f1565b604051808215151515815260200191505060405180910390f35b34801561012f57600080fd5b5061014e600480360381019080803590602001909291905050506104b3565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561018e578082015181840152602081019050610173565b50505050905090810190601f1680156101bb5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101d557600080fd5b50610276600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506107aa565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102b657808201518184015260208101905061029b565b50505050905090810190601f1680156102e35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000816040516020018082805190602001908083835b60208310151561032c5780518252602082019150602081019050602083039250610307565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156103955780518252602082019150602081019050602083039250610370565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916836040516020018082805190602001908083835b6020831015156103ff57805182526020820191506020810190506020830392506103da565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156104685780518252602082019150602081019050602083039250610443565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614156104a857600190506104ad565b600090505b92915050565b60606104bd61097b565b60606000610140604051908101604052806040805190810160405280600181526020017f300000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f310000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f320000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f330000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f340000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f350000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f360000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f370000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f380000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f3900000000000000000000000000000000000000000000000000000000000000815250815250925084905082600a808381151561072d57fe5b04028203600a8110151561073d57fe5b60200201519150600a8181151561075057fe5b0490505b600081111561079f5761078983600a808481151561076e57fe5b04028303600a8110151561077e57fe5b6020020151836107aa565b9150600a8181151561079757fe5b049050610754565b819350505050919050565b606080606080606060008088955087945084518651016040519080825280601f01601f1916602001820160405280156107f25781602001602082028038833980820191505090505b50935083925060009150600090505b85518110156108b457858181518110151561081857fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002838380600101945081518110151561087757fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610801565b600090505b845181101561096c5784818151811015156108d057fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002838380600101945081518110151561092f57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535080806001019150506108b9565b83965050505050505092915050565b61014060405190810160405280600a905b606081526020019060019003908161098c57905050905600a165627a7a723058204e31c618a6d6b07b81a518cd953676ef6027998a7d8fc335256dcd3ddfe7b53d0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"_a\",\"type\":\"string\"},{\"name\":\"_b\",\"type\":\"string\"}],\"name\":\"stringEqual\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_input\",\"type\":\"uint256\"}],\"name\":\"getIntToString\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_a\",\"type\":\"string\"},{\"name\":\"_b\",\"type\":\"string\"}],\"name\":\"strConcat\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_STRINGEQUAL = "stringEqual";

    public static final String FUNC_GETINTTOSTRING = "getIntToString";

    public static final String FUNC_STRCONCAT = "strConcat";

    @Deprecated
    protected StringProcess(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StringProcess(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StringProcess(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StringProcess(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Boolean> stringEqual(String _a, String _b) {
        final Function function = new Function(FUNC_STRINGEQUAL,
                Arrays.<Type>asList(new Utf8String(_a),
                        new Utf8String(_b)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> getIntToString(BigInteger _input) {
        final Function function = new Function(FUNC_GETINTTOSTRING,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_input)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> strConcat(String _a, String _b) {
        final Function function = new Function(FUNC_STRCONCAT,
                Arrays.<Type>asList(new Utf8String(_a),
                        new Utf8String(_b)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static StringProcess load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringProcess(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StringProcess load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringProcess(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StringProcess load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StringProcess(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StringProcess load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StringProcess(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StringProcess> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StringProcess.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StringProcess> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringProcess.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StringProcess> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StringProcess.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StringProcess> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringProcess.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
