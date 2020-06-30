package org.fisco.bcos.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class User extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b50620000996040805190810160405280600581526020017f61646d696e0000000000000000000000000000000000000000000000000000008152506040805190810160405280600681526020017f31323334353600000000000000000000000000000000000000000000000000008152506001620000a0640100000000026401000000009004565b5062000623565b6000806000809150600090505b600080549050811015620002b057856040516020018082805190602001908083835b602083101515620000f65780518252602082019150602081019050602083039250620000cf565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156200016157805182526020820191506020810190506020830392506200013a565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600082815481101515620001a257fe5b90600052602060002090600402016000016040516020018082805460018160011615610100020316600290048015620002155780601f10620001f257610100808354040283529182019162000215565b820191906000526020600020905b81548152906001019060200180831162000200575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151562000260578051825260208201915060208101905060208303925062000239565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415620002a25760019150620002b0565b8080600101915050620000ad565b60018260ff161415156200044b5783156200038a576000608060405190810160405280888152602001878152602001614e208152602001600115158152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000190805190602001906200033792919062000574565b5060208201518160010190805190602001906200035692919062000574565b506040820151816002015560608201518160030160006101000a81548160ff0219169083151502179055505050506200044a565b6000608060405190810160405280888152602001878152602001612710815260200160001515815250908060018154018082558091505090600182039060005260206000209060040201600090919290919091506000820151816000019080519060200190620003fc92919062000574565b5060208201518160010190805190602001906200041b92919062000574565b506040820151816002015560608201518160030160006101000a81548160ff0219169083151502179055505050505b5b7f097a73d7a03ee68f9e531f3d729c5a39bea1d5c36583e36c08eb1b76e954904a828787604051808460ff1660ff1681526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015620004c1578082015181840152602081019050620004a4565b50505050905090810190601f168015620004ef5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156200052a5780820151818401526020810190506200050d565b50505050905090810190601f168015620005585780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a181925050509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620005b757805160ff1916838001178555620005e8565b82800160010185558215620005e8579182015b82811115620005e7578251825591602001919060010190620005ca565b5b509050620005f79190620005fb565b5090565b6200062091905b808211156200061c57600081600090555060010162000602565b5090565b90565b6115c280620006336000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806338639f7b146100725780633a51d246146100fd57806358467dbc1461017a578063d7fc1b9b14610243578063df021a1714610318575b600080fd5b34801561007e57600080fd5b506100e3600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610399565b604051808215151515815260200191505060405180910390f35b34801561010957600080fd5b50610164600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506107eb565b6040518082815260200191505060405180910390f35b34801561018657600080fd5b50610227600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a30565b604051808260ff1660ff16815260200191505060405180910390f35b34801561024f57600080fd5b506102fc600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803515159060200190929190505050610e0a565b604051808260ff1660ff16815260200191505060405180910390f35b34801561032457600080fd5b5061037f600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506112be565b604051808215151515815260200191505060405180910390f35b600080600083101561045f577f2afb7ed23fa07685d0e7a6c47957e34a586646161b75eda335171628a66b0ff460008585604051808415151515815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561041a5780820151818401526020810190506103ff565b50505050905090810190601f1680156104475780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a1600091506107e4565b600090505b60008054905081101561072e57836040516020018082805190602001908083835b6020831015156104aa5780518252602082019150602081019050602083039250610485565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561051357805182526020820191506020810190506020830392506104ee565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008281548110151561055357fe5b906000526020600020906004020160000160405160200180828054600181600116156101000203166002900480156105c25780601f106105a05761010080835404028352918201916105c2565b820191906000526020600020905b8154815290600101906020018083116105ae575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561060b57805182526020820191506020810190506020830392506105e6565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610721578260008281548110151561065257fe5b9060005260206000209060040201600201819055507f2afb7ed23fa07685d0e7a6c47957e34a586646161b75eda335171628a66b0ff460018585604051808415151515815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156106dc5780820151818401526020810190506106c1565b50505050905090810190601f1680156107095780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a1600191506107e4565b8080600101915050610464565b7f2afb7ed23fa07685d0e7a6c47957e34a586646161b75eda335171628a66b0ff460008585604051808415151515815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156107a3578082015181840152602081019050610788565b50505050905090810190601f1680156107d05780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a1600091505b5092915050565b600080600090505b600080549050811015610a0657826040516020018082805190602001908083835b6020831015156108395780518252602082019150602081019050602083039250610814565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156108a2578051825260208201915060208101905060208303925061087d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000828154811015156108e257fe5b906000526020600020906004020160000160405160200180828054600181600116156101000203166002900480156109515780601f1061092f576101008083540402835291820191610951565b820191906000526020600020905b81548152906001019060200180831161093d575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561099a5780518252602082019150602081019050602083039250610975565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614", "156109f9576000818154811015156109e057fe5b9060005260206000209060040201600201549150610a2a565b80806001019150506107f3565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff91505b50919050565b600080600090505b600080549050811015610dfe57836040516020018082805190602001908083835b602083101515610a7e5780518252602082019150602081019050602083039250610a59565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610ae75780518252602082019150602081019050602083039250610ac2565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600082815481101515610b2757fe5b90600052602060002090600402016000016040516020018082805460018160011615610100020316600290048015610b965780601f10610b74576101008083540402835291820191610b96565b820191906000526020600020905b815481529060010190602001808311610b82575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515610bdf5780518252602082019150602081019050602083039250610bba565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610df157826040516020018082805190602001908083835b602083101515610c4f5780518252602082019150602081019050602083039250610c2a565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610cb85780518252602082019150602081019050602083039250610c93565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600082815481101515610cf857fe5b90600052602060002090600402016001016040516020018082805460018160011615610100020316600290048015610d675780601f10610d45576101008083540402835291820191610d67565b820191906000526020600020905b815481529060010190602001808311610d53575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515610db05780518252602082019150602081019050602083039250610d8b565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610df05760009150610e03565b5b8080600101915050610a38565b600191505b5092915050565b6000806000809150600090505b60008054905081101561100b57856040516020018082805190602001908083835b602083101515610e5d5780518252602082019150602081019050602083039250610e38565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610ec65780518252602082019150602081019050602083039250610ea1565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600082815481101515610f0657fe5b90600052602060002090600402016000016040516020018082805460018160011615610100020316600290048015610f755780601f10610f53576101008083540402835291820191610f75565b820191906000526020600020905b815481529060010190602001808311610f61575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515610fbe5780518252602082019150602081019050602083039250610f99565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610ffe576001915061100b565b8080600101915050610e17565b60018260ff1614151561119b5783156110de576000608060405190810160405280888152602001878152602001614e2081526020016001151581525090806001815401808255809150509060018203906000526020600020906004020160009091929091909150600082015181600001908051906020019061108e9291906114f1565b5060208201518160010190805190602001906110ab9291906114f1565b506040820151816002015560608201518160030160006101000a81548160ff02191690831515021790555050505061119a565b600060806040519081016040528088815260200187815260200161271081526020016000151581525090806001815401808255809150509060018203906000526020600020906004020160009091929091909150600082015181600001908051906020019061114e9291906114f1565b50602082015181600101908051906020019061116b9291906114f1565b506040820151816002015560608201518160030160006101000a81548160ff0219169083151502179055505050505b5b7f097a73d7a03ee68f9e531f3d729c5a39bea1d5c36583e36c08eb1b76e954904a828787604051808460ff1660ff1681526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561120f5780820151818401526020810190506111f4565b50505050905090810190601f16801561123c5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561127557808201518184015260208101905061125a565b50505050905090810190601f1680156112a25780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a181925050509392505050565b600080600090505b6000805490508110156114e657826040516020018082805190602001908083835b60208310151561130c57805182526020820191506020810190506020830392506112e7565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156113755780518252602082019150602081019050602083039250611350565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000828154811015156113b557fe5b906000526020600020906004020160000160405160200180828054600181600116156101000203166002900480156114245780601f10611402576101008083540402835291820191611424565b820191906000526020600020905b815481529060010190602001808311611410575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561146d5780518252602082019150602081019050602083039250611448565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614156114d9576000818154811015156114b357fe5b906000526020600020906004020160030160009054906101000a900460ff1691506114eb565b80806001019150506112c6565b600091505b50919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061153257805160ff1916838001178555611560565b82800160010185558215611560579182015b8281111561155f578251825591602001919060010190611544565b5b50905061156d9190611571565b5090565b61159391905b8082111561158f576000816000905550600101611577565b5090565b905600a165627a7a72305820c934fa648b5f0b7bfe135762b9b89138ed0005a55581dee86ae356c7243bafc40029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"_balance\",\"type\":\"uint256\"}],\"name\":\"setBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"}],\"name\":\"getBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"_password\",\"type\":\"string\"}],\"name\":\"login\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"_password\",\"type\":\"string\"},{\"name\":\"_isAdmin\",\"type\":\"bool\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"}],\"name\":\"isAdmin\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_ret\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"_id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"_password\",\"type\":\"string\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_ret\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"_id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"balance\",\"type\":\"uint256\"}],\"name\":\"SetBalanceEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_SETBALANCE = "setBalance";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_LOGIN = "login";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_ISADMIN = "isAdmin";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
            }, new TypeReference<Utf8String>() {
            }, new TypeReference<Utf8String>() {
            }));
    ;

    public static final Event SETBALANCEEVENT_EVENT = new Event("SetBalanceEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
            }, new TypeReference<Utf8String>() {
            }, new TypeReference<Uint256>() {
            }));
    ;

    @Deprecated
    protected User(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected User(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected User(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected User(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> setBalance(String _id, BigInteger _balance) {
        final Function function = new Function(
                FUNC_SETBALANCE,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Uint256(_balance)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBalance(String _id, BigInteger _balance, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBALANCE,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Uint256(_balance)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBalanceSeq(String _id, BigInteger _balance) {
        final Function function = new Function(
                FUNC_SETBALANCE,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Uint256(_balance)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getSetBalanceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETBALANCE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }, new TypeReference<Uint256>() {
                }));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        ;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue()
        );
    }

    public Tuple1<Boolean> getSetBalanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETBALANCE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        ;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<BigInteger> getBalance(String _id) {
        final Function function = new Function(FUNC_GETBALANCE,
                Arrays.<Type>asList(new Utf8String(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> login(String _id, String _password) {
        final Function function = new Function(FUNC_LOGIN,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Utf8String(_password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> register(String _id, String _password, Boolean _isAdmin) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Utf8String(_password),
                        new Bool(_isAdmin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String _id, String _password, Boolean _isAdmin, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Utf8String(_password),
                        new Bool(_isAdmin)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerSeq(String _id, String _password, Boolean _isAdmin) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new Utf8String(_id),
                        new Utf8String(_password),
                        new Bool(_isAdmin)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, Boolean> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Bool>() {
                }));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        ;
        return new Tuple3<String, String, Boolean>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (Boolean) results.get(2).getValue()
        );
    }

    public Tuple1<BigInteger> getRegisterOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REGISTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        ;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public RemoteCall<Boolean> isAdmin(String _id) {
        final Function function = new Function(FUNC_ISADMIN,
                Arrays.<Type>asList(new Utf8String(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._password = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRegisterEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI, BINARY, topic0, fromBlock, toBlock, otherTopcs, callback);
    }

    public void registerRegisterEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI, BINARY, topic0, callback);
    }

    public List<SetBalanceEventEventResponse> getSetBalanceEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETBALANCEEVENT_EVENT, transactionReceipt);
        ArrayList<SetBalanceEventEventResponse> responses = new ArrayList<SetBalanceEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetBalanceEventEventResponse typedResponse = new SetBalanceEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._ret = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSetBalanceEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETBALANCEEVENT_EVENT);
        registerEventLogPushFilter(ABI, BINARY, topic0, fromBlock, toBlock, otherTopcs, callback);
    }

    public void registerSetBalanceEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETBALANCEEVENT_EVENT);
        registerEventLogPushFilter(ABI, BINARY, topic0, callback);
    }

    @Deprecated
    public static User load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new User(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static User load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new User(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static User load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new User(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static User load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new User(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<User> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(User.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<User> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(User.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<User> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(User.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<User> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(User.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public BigInteger _ret;

        public String _id;

        public String _password;
    }

    public static class SetBalanceEventEventResponse {
        public Log log;

        public Boolean _ret;

        public String _id;

        public BigInteger balance;
    }
}
