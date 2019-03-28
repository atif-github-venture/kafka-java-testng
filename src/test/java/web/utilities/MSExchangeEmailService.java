//package web.utilities;
//
//import microsoft.exchange.webservices.data.core.ExchangeService;
//import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
//import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
//import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
//import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
//import microsoft.exchange.webservices.data.core.service.item.Item;
//import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
//import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
//import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
//import microsoft.exchange.webservices.data.credential.WebCredentials;
//import microsoft.exchange.webservices.data.search.FindItemsResults;
//import microsoft.exchange.webservices.data.search.ItemView;
//import microsoft.exchange.webservices.data.search.filter.SearchFilter;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.joda.time.DateTime;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//import java.time.Instant;
//
//import static junit.framework.TestCase.fail;
//
//@Component
//@Scope("cucumber-glue")
//public class MSExchangeEmailService {
//
//    private static Logger log = LogManager.getLogger(MSExchangeEmailService.class.getName());
//
//    private ExchangeService service = null;
//    private final Integer NUMBER_EMAILS_FETCH = 5;
//    private final int pollTime = 30000;
//    private final int pollWait = 3000;
//    private FindItemsResults<Item> emails = null;
//    private SearchFilter searchFilter;
//
//
//    public void connect(String username , String password) {
//        service = new ExchangeService();
//        try {
//            service.setUrl(new URI(FakerUtil.getInstance().readStaticDataFromYaml("mailServer")));
//            ExchangeCredentials credentials = new WebCredentials(username, password);
//            service.setCredentials(credentials);
//            searchFilter = new SearchFilter.SearchFilterCollection(LogicalOperator.And, unReadFilter(), dateFilter());
//        } catch (Exception e) {
//            log.error("error occured {}", e);
//            fail(String.format("MSExchangeEmailService: Error Occured while connecting to Mail Exchange Server, for the fate=%s, exception=%s",JVMOptions.getInstance().getJirastory(),e));
//
//        }
//    }
//
//    public void disconnect() {
//        service = null;
//    }
//
//    private boolean checkEmailReceived(String subject) {
//        for (Item email : this.emails)
//            try {
//            if(email.getSubject().trim().contentEquals(subject.trim())) {
//                EmailMessage message = EmailMessage.bind(service, email.getId());
//                message.setIsRead(true);
//                message.update(ConflictResolutionMode.AlwaysOverwrite);
//                log.info("actual email received is -> {} ", email.getSubject());
//                return true;
//            }
//            } catch (Exception e) {
//                log.error("error occured {}", e);
//            }
//        return false;
//    }
//
//
//    public boolean isReceivedEmail(String subject) {
//
//        int fetchTime = pollTime;
//
//        long maxWaitTIme = Instant.now().toEpochMilli() + pollTime;
//        log.info(" Email Fetching started at = {}" , Instant.now());
//        while (Instant.now().toEpochMilli() < maxWaitTIme) {
//            log.info("Reading \'UnReadEmails\', time={}", Instant.now());
//            getUnreadEmails();
//            if (checkEmailReceived(subject)) {
//                log.info("Received Email with subject -> {} ", subject);
//                return true;
//            }
//            try {
//                log.info("MSExchangeEmailService:isReceivedEmail, sleeping for {} milliseconds", pollWait);
//                Thread.sleep(pollWait);
//            } catch (InterruptedException e) {
//                log.error("error occured ->  {}", e);
//            }
//        }
//        return false;
//    }
//
//    private void getUnreadEmails() {
//        try {
//            this.emails = service.findItems(WellKnownFolderName.Inbox, this.searchFilter, new ItemView(NUMBER_EMAILS_FETCH));
//        } catch (Exception e) {
//            this.emails = null;
//            log.error("error occured", e);
//            fail(String.format("MSExchangeEmailService: Error Occured while getting emails, for the fate=%s, exception=%s",JVMOptions.getInstance().getJirastory(),e));
//        }
//    }
//
//    private SearchFilter dateFilter() {
//        DateTime searchdate = DateTime.now().minusMinutes(3);
//        return new SearchFilter.IsGreaterThan(ItemSchema.DateTimeReceived, searchdate.toDate());
//
//    }
//
//    private SearchFilter unReadFilter() {
//        return new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false);
//    }
//
//
//}
