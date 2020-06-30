package org.fisco.bcos.blockService;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.contract.Order;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;


public class OrderService {

    private Logger logger = LoggerFactory.getLogger(org.fisco.bcos.Application.class);
    private BigInteger gasPrice = new BigInteger("30000000");
    private BigInteger gasLimit = new BigInteger("30000000");

    private Web3j web3j;
    private Credentials credentials;

    // 单例模式
    private static OrderService orderService;

    public static synchronized OrderService getOrderService() throws Exception {
        if (orderService == null)
            orderService = new OrderService();
        return orderService;
    }

    private OrderService() throws Exception {

        // init the Service
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        Web3j web3j = Web3j.build(channelEthereumService, 1);

        // init Credentials
        Credentials credentials = Credentials.create(Keys.createEcKeyPair());

        setCredentials(credentials);
        setWeb3j(web3j);

        logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);

        // TODO
        this.deployAssetAndRecordAddr();
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger _logger) {
        this.logger = _logger;
    }

    public BigInteger getGasPrice() {
        return this.gasPrice;
    }

    public void setGasPrice(BigInteger _gasPrice) {
        this.gasPrice = _gasPrice;
    }

    public BigInteger getGasLimit() {
        return this.gasLimit;
    }

    public void setGasLimit(BigInteger _gasLimit) {
        this.gasLimit = _gasLimit;
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from order.contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("order.contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load order contract address failed, please deploy it first. ");
        }
        logger.info(" load order address from order.contract.properties, address is {}", contractAddress);
        return contractAddress;
    }

    public void deployAssetAndRecordAddr() {
        try {
            Order order = Order.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy order success, contract address is " + order.getContractAddress());
            recordAssetAddr(order.getContractAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy order contract failed, error message is  " + e.getMessage());
        }
    }

    public void recordAssetAddr(String address) throws IOException {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("order.contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }
}
