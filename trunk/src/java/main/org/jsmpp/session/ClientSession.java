package org.jsmpp.session;

import java.io.IOException;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUStringException;
import org.jsmpp.bean.Address;
import org.jsmpp.bean.DataCoding;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.OptionalParameter;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.ReplaceIfPresentFlag;
import org.jsmpp.bean.SubmitMultiResult;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;

/**
 * This interface provides all operation that the client session can do. It
 * doesn't distinct the operation of specific session type (Transmitter,
 * Receiver) it's just like Transceiver. The distinction might should be
 * recognized in a different way, such as by user code when they do a binding or
 * by throwing exception when invoking illegal operation.
 * 
 * @author uudashr
 * 
 */
public interface ClientSession extends Session {
    
    /**
     * Submit a short message to specified destination address. This method will
     * blocks until response received or timeout reached. This method simplify
     * operations of sending SUBMIT_SM command and receiving the SUBMIT_SM_RESP.
     * 
     * @param serviceType is the service_type.
     * @param sourceAddrTon is the source_addr_ton.
     * @param sourceAddrNpi is the source_addr_npi.
     * @param sourceAddr is the source_addr.
     * @param destAddrTon is the dest_addr_ton.
     * @param destAddrNpi is the dest_addr_npi.
     * @param destinationAddr is the destination_addr.
     * @param esmClass is the esm_class.
     * @param protocolId is the protocol_id.
     * @param priorityFlag is the priority_flag.
     * @param scheduleDeliveryTime is the schedule_delivery_time.
     * @param validityPeriod is the validity_period.
     * @param registeredDelivery is the registered_delivery.
     * @param replaceIfPresentFlag is the replace_if_present_flag.
     * @param dataCoding is the data_coding.
     * @param smDefaultMsgId is the sm_default_msg_id.
     * @param shortMessage is the short_message.
     * @param optionalParameters is the optional parameters.
     * @return the message_id to identified the submitted short message for
     *         later use (delivery receipt, QUERY_SM, CANCEL_SM, REPLACE_SM).
     * @throws PDUStringException if there is invalid string found.
     * @throws ResponseTimeoutException if timeout has been reach.
     * @throws InvalidResponseException if response is invalid.
     * @throws NegativeResponseException if negative response received.
     * @throws IOException if there is an I/O error found.
     */
    String submitShortMessage(String serviceType, TypeOfNumber sourceAddrTon,
            NumberingPlanIndicator sourceAddrNpi, String sourceAddr,
            TypeOfNumber destAddrTon, NumberingPlanIndicator destAddrNpi,
            String destinationAddr, ESMClass esmClass, byte protocolId,
            byte priorityFlag, String scheduleDeliveryTime,
            String validityPeriod, RegisteredDelivery registeredDelivery,
            byte replaceIfPresentFlag, DataCoding dataCoding,
            byte smDefaultMsgId, byte[] shortMessage,
            OptionalParameter... optionalParameters) throws PDUStringException,
            ResponseTimeoutException, InvalidResponseException,
            NegativeResponseException, IOException;

    /**
     * Submit short message to multiple destination address. It's similar to
     * submit short message, but it sending to multiple address. This method
     * will blocks until response received or timeout reached. This method is
     * simplify operations of sending SUBMIT_MULTI and receiving
     * SUBMIT_MULTI_RESP.
     * 
     * @param serviceType is the service_type.
     * @param sourceAddrTon is the source_addr_ton.
     * @param sourceAddrNpi is the source_addr_npi.
     * @param sourceAddr is the source_addr.
     * @param destinationAddresses is the destination addresses.
     * @param esmClass is the esm_class.
     * @param protocolId is the protocol_id.
     * @param priorityFlag is the priority_flag.
     * @param scheduleDeliveryTime is the schedule_delivery_time.
     * @param validityPeriod is the validity_period.
     * @param registeredDelivery is the registered_delivery.
     * @param replaceIfPresentFlag is the replace_if_present_flag.
     * @param dataCoding is the data_coding.
     * @param smDefaultMsgId is the sm_default_msg_id.
     * @param shortMessage is the short_message.
     * @param optionalParameters is the optional parameters.
     * @return the message_id and the un-success deliveries.
     * @throws PDUStringException if there is invalid string found.
     * @throws ResponseTimeoutException if timeout has been reach.
     * @throws InvalidResponseException if response is invalid.
     * @throws NegativeResponseException if negative response received.
     * @throws IOException if there is an I/O error found.
     */
    SubmitMultiResult submitMultiple(String serviceType,
            TypeOfNumber sourceAddrTon, NumberingPlanIndicator sourceAddrNpi,
            String sourceAddr, Address[] destinationAddresses,
            ESMClass esmClass, byte protocolId, byte priorityFlag,
            String scheduleDeliveryTime, String validityPeriod,
            RegisteredDelivery registeredDelivery,
            ReplaceIfPresentFlag replaceIfPresentFlag, DataCoding dataCoding,
            byte smDefaultMsgId, byte[] shortMessage,
            OptionalParameter[] optionalParameters) throws PDUStringException,
            ResponseTimeoutException, InvalidResponseException,
            NegativeResponseException, IOException;

    /**
     * Query previous submitted short message based on it's message_id and
     * message_id. This method will blocks until response received or timeout
     * reached. This method is simplify operations of sending QUERY_SM and
     * receiving QUERY_SM_RESP.
     * 
     * @param messageId is the message_id.
     * @param sourceAddrTon is the source_addr_ton.
     * @param sourceAddrNpi is the source_addr_npi.
     * @param sourceAddr is the source_addr.
     * @return the result of query short message.
     * @throws PDUStringException if there is invalid string found.
     * @throws ResponseTimeoutException if timeout has been reach.
     * @throws InvalidResponseException if response is invalid.
     * @throws NegativeResponseException if negative response received.
     * @throws IOException if there is an I/O error found.
     */
    QuerySmResult queryShortMessage(String messageId, TypeOfNumber sourceAddrTon,
            NumberingPlanIndicator sourceAddrNpi, String sourceAddr)
            throws PDUStringException, ResponseTimeoutException,
            InvalidResponseException, NegativeResponseException, IOException;

    /**
     * 
     * Cancel the previous submitted short message. This method will blocks
     * until response received or timeout reached. This method is simplify
     * operations of sending CANCEL_SM and receiving CANCEL_SM_RESP.
     * 
     * @param serviceType is the service_type.
     * @param messageId is the message_id.
     * @param sourceAddrTon is the source_addr_ton.
     * @param sourceAddrNpi is the source_addr_npi.
     * @param sourceAddr is the source_addr.
     * @param destAddrTon is the dest_addr_ton.
     * @param destAddrNpi is the dest_addr_npi.
     * @param destinationAddress is destination_address.
     * @throws PDUStringException if there is invalid string found.
     * @throws ResponseTimeoutException if timeout has been reach.
     * @throws InvalidResponseException if response is invalid.
     * @throws NegativeResponseException if negative response received.
     * @throws IOException if there is an I/O error found.
     */
    void cancelShortMessage(String serviceType, String messageId,
            TypeOfNumber sourceAddrTon, NumberingPlanIndicator sourceAddrNpi,
            String sourceAddr, TypeOfNumber destAddrTon,
            NumberingPlanIndicator destAddrNpi, String destinationAddress)
            throws PDUStringException, ResponseTimeoutException,
            InvalidResponseException, NegativeResponseException, IOException;

    /**
     * Replace the previous submitted short message. This method will blocks
     * until response received or timeout reached. This method is simplify
     * operations of sending REPLACE_SM and receiving REPLACE_SM_RESP.
     * 
     * @param messageId is the message_id.
     * @param sourceAddrTon is the source_addr_ton.
     * @param sourceAddrNpi is the source_addr_npi.
     * @param sourceAddr is the source_addr.
     * @param scheduleDeliveryTime is the schedule_delivery_time.
     * @param validityPeriod is the validity_period.
     * @param registeredDelivery is the registered_delivery.
     * @param smDefaultMsgId is the sm_default_msg_id.
     * @param shortMessage is the short_message.
     * @throws PDUStringException if there is invalid string found.
     * @throws ResponseTimeoutException if timeout has been reach.
     * @throws InvalidResponseException if response is invalid.
     * @throws NegativeResponseException if negative response received.
     * @throws IOException if there is an I/O error found.
     */
    void replaceShortMessage(String messageId, TypeOfNumber sourceAddrTon,
            NumberingPlanIndicator sourceAddrNpi, String sourceAddr,
            String scheduleDeliveryTime, String validityPeriod,
            RegisteredDelivery registeredDelivery, byte smDefaultMsgId,
            byte[] shortMessage) throws PDUStringException,
            ResponseTimeoutException, InvalidResponseException,
            NegativeResponseException, IOException;
}