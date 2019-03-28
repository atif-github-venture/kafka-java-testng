package api.test;

import kafka.KafkaTopicQuery;
import org.testng.annotations.Test;
import web.utilities.JsonHelper;
import web.utilities.ThreadLocalMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static org.junit.Assert.fail;

public class ProductDataGenerator {
    private JsonHelper jsonHelper = new JsonHelper();

    @Test()
    public void getProductBaseData() {
        searchForProductPageInstance();
        readCollectionRoadMapping_fromJSon();
        readProductChoiceGroupId_fromJSon();
        searchForTypePageForCollectionDoc();
        System.out.println(ThreadLocalMap.getMap());
    }

    @Test()
    public void pushDataToKafka() {
//        pushToKafka();
//        System.out.println("pushed");
    }

    private void readCollectionRoadMapping_fromJSon() {
        try {
            String rmc = jsonHelper.readJsonPath(ThreadLocalMap.getMap().get("ppi").toString(), "dataSource.items.cmsdigitalcontent:roadMappingCollections");
            List<String> list = Pattern.compile(",").splitAsStream(rmc.replaceAll("\\[|\\]|\"", "")).map(String::valueOf).collect(Collectors.toList());
            if ((list.size() > 0)) {
                ThreadLocalMap.getMap().put("roadMappingCollections", list);
            } else {
                fail("Road map collection is empty in PPI response data!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readProductChoiceGroupId_fromJSon() {
        try {
            String pcgLinkId = jsonHelper.readJsonPath(ThreadLocalMap.getMap().get("ppi").toString(), "dataSource.items.cmsdigitalcontent:productChoiceGroup.link.id");
            if(pcgLinkId.isEmpty()){
                fail("Product Choice Group is empty in PPI response data!");
            }
            ThreadLocalMap.getMap().put("productChoiceGroupId", pcgLinkId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchForTypePageForCollectionDoc() {
        List<String> rmc1 = ThreadLocalMap.getItem("roadMappingCollections", List.class);
        List typePage = new KafkaTopicQuery().simpleConsumerQuery("CmsDocuments", new String[]{rmc1.get(0), "\"type\": \"page\""}, "keyandvalue");
        ThreadLocalMap.getMap().put("typePage", typePage);
    }

    private void searchForProductPageInstance() {
//        List ppi = new KafkaTopicQuery().simpleConsumerQuery("CmsDocuments", new String[]{"cmsdigitalcontent:ProductPageInstance", "productChoiceGroup", "roadMappingCollections"}, "value");
        List ppi = new KafkaTopicQuery().simpleConsumerQuery("CmsDocuments", new String[]{"0d82539b-b89c-43c6-9f07-0307537c5c58","cmsdigitalcontent:ProductPageInstance", "productChoiceGroup", "roadMappingCollections"}, "keyandvalue");
        if(ppi.size()>0){
            ThreadLocalMap.getMap().put("ppi", ppi.get(1));
        }else
            fail("The query didn't return any ppi data from CMS");
    }

    private void pushToKafka() {
//        List ppi = new KafkaTopicQuery().simpleConsumerQuery("CmsDocuments", new String[]{"cmsdigitalcontent:ProductPageInstance", "productChoiceGroup", "roadMappingCollections"}, "value");
        new KafkaTopicQuery().runProducer();

    }
}
