package sdp.Endpoints;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import sdp.Config.ClientServiceConfig;
import sdp.Model.Header.NotifySOAPHeader;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.Amount.ChargeAmount;
import sdp.Xsd.Amount.RefundAmount;
import sdp.Xsd.ReserveAmountCharging.ChargeReservationReq;
import sdp.Xsd.ReserveAmountCharging.ReleaseReservationReq;
import sdp.Xsd.ReserveAmountCharging.ReserveAmountReq;
import sdp.Xsd.Volume.ChargeVolume;
import sdp.Xsd.Volume.RefundVolume;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Created by Atiq on 11/15/2017.
 */
/**
 * FOLLOWING PaymentEndPoint CLASS IS GENERATED FOR
 * ================================================
 * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
 * Impact ->  Using SDP provided Payment capability application programming interfaces (APIs)
 * to connect to it and use its Payment capability to deduct and refund by fee, deduct by volume,
 * and amount reservation, fee deduction from the reserved amount, and reservation cancellation.
 *
 * */
@Endpoint
public class PaymentEndPoint {

    @Autowired
    Utility utility;
    @Value("${amount.uri}")
    private String amountUri;
    @Value("${amount.context}")
    private String amountContext;
    @Value("${volume.uri}")
    private String volumeUri;
    @Value("${volume.context}")
    private String volumeContext;
    @Value("${reserve.amount.charging.uri}")
    private String rsrvAmntChargingUri;
    @Value("${reserve.amount.charging.context}")
    private String rsrvAmntChargingContext;

    private HttpServletRequest httpServletRequest;

    private static final String amountNS = "http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local";
    private static final String volumeNS = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local";
    private static final String reserveAmntChargingNS = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local";


    @Autowired
    ClientServiceConfig config;


    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    /**
     * FOLLOWING chargeAmountResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [2.2 chargeAmount]
     * Base URL -> http://IP:Port/AmountChargingService/services/AmountCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local
     * Local Part -> chargeAmount
     * Type -> Performing as a client
     * Impact -> Invoke the chargeAmount API provided by the SDP(functioning as the Provider)
     * to deduct by fee from users accounts.
     * */
    @PayloadRoot(namespace = amountNS, localPart = "chargeAmount")
    @ResponsePayload
    public Response chargeAmountResponse(@RequestPayload ChargeAmount request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.chargeAmountResponse(amountContext, amountUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING refundAmountResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [3.2 refundAmount]
     * Base URL -> http://IP:Port/AmountChargingService/services/AmountCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local
     * Local Part -> refundAmount
     * Type -> Performing as a client
     * Impact -> Invoke the refundAmount API provided by the SDP(functioning as the provider)
     * to refund by fees to users when charging fails.
     */
    @PayloadRoot(namespace = amountNS, localPart = "refundAmount")
    @ResponsePayload
    public Response refundAmountResponse(@RequestPayload RefundAmount request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.refundAmountResponse(amountContext, amountUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500,ex.getMessage(),ex.toString());
        }
    }

    /**
     * FOLLOWING chargeVolumeResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [2.3 chargeVolume]
     * Base URL -> http://IP:Port/VolumeChargingService/services/VolumeCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local
     * Local Part -> chargeVolume
     * Type -> Performing as a client
     * Impact -> Invoke the chargeVolume API provided by the SDP(functioning as the server)
     * to deduct by volume from users accounts.
     */
    @PayloadRoot(namespace = volumeNS, localPart = "chargeVolume")
    @ResponsePayload
    public Response chargeVolumeResponse(@RequestPayload ChargeVolume request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.chargeVolumeResponse(volumeContext, volumeUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING refundVolumeResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [3.3 refundVolume]
     * Base URL -> http://IP:Port/VolumeChargingService/services/VolumeCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local
     * Local Part -> refundVolume
     * Type -> Performing as a client
     * Impact -> Invoke the refundVolume API provided by the SDP(functioning as the provider)
     * to refund by volume to users when charging fails.
     */
    @PayloadRoot(namespace = volumeNS, localPart = "refundVolume")
    @ResponsePayload
    public Response refundVolumeResponse(@RequestPayload RefundVolume request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.refundVolumeResponse(volumeContext, volumeUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500,ex.getMessage(),ex.toString());
        }
    }

    /**
     * FOLLOWING callRsrvAmntRequest METHOD IS GENERATED FOR
     * =====================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [4.2 reserveAmount]
     * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
     * Local Part -> reserveAmountReq
     * Type -> Performing as a client
     * Impact -> Managing 'requested' data to sends a reserveAmountRequest
     * message to the SDP to reserve amount for users.
     */
    @PayloadRoot(namespace = reserveAmntChargingNS, localPart = "reserveAmountReq")
    @ResponsePayload
    public Response reserveAmount(@RequestPayload ReserveAmountReq request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.getReserveAmntRspnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING callChrgResrvRequest METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [4.3 chargeReservation]
     * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
     * Local Part -> chargeReservationReq
     * Type -> Performing as a client
     * Impact -> Managing 'requested' data to sends a chargeReservationRequest
     * message to the SDP to deduct fees from users' reserved amount.
     */
    @PayloadRoot(namespace = reserveAmntChargingNS, localPart = "chargeReservationReq")
    @ResponsePayload
    public Response callChrgResrvRequest(@RequestPayload ChargeReservationReq request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.getChargeResrvtnRespnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING callReleaseRsrvRequest METHOD IS GENERATED FOR
     * ========================================================
     * Documentation -> SDP Solution API Reference (Payment, ParlayX 2.1)
     * Content Number -> [4.4 releaseReservation]
     * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
     * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
     * Local Part -> releaseReservationReq
     * Type -> Performing as a client
     * Impact -> Managing 'requested' data to sends a releaseReservationRequest
     * message to the SDP to cancel users' reserved amount.
     */
    @PayloadRoot(namespace = reserveAmntChargingNS, localPart = "releaseReservationReq")
    @ResponsePayload
    public Response callReleaseRsrvRequest(@RequestPayload ReleaseReservationReq request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.getReleaseResrvRspnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }


    public NotifySOAPHeader getAuthentication(SoapHeaderElement header) {
        NotifySOAPHeader authentication = null;
        try {
            JAXBContext context = JAXBContext.newInstance(NotifySOAPHeader.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (NotifySOAPHeader) unmarshaller.unmarshal(header.getSource());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return authentication;
    }

}
