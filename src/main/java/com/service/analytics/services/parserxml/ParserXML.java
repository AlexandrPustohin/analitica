package com.service.analytics.services.parserxml;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.dto.SaleDTO;
import com.service.analytics.services.saleservice.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
@Component
public class ParserXML {
    @Autowired
    SaleServiceImpl saleService;

    //TODO - добавить логгирование
    public  void parse(File filepath) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = filepath;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
              builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            // получаем узлы с именем SALE
            // теперь XML полностью загружен в память
            // в виде объекта Document
            NodeList nodeList = doc.getElementsByTagName("SALE");

            // создадим из него список объектов Sale
            List<SaleDTO> saleDTOS;
            Stream<Node> nodeStream = IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);

            saleDTOS = nodeStream.map(node -> getSale(node)).collect(Collectors.toList());
            //сохраняем в базу
            System.out.println("Сохраняем в базу...");
            saleService.saveAll(saleDTOS);


            // печатаем в консоль информацию по каждому объекту Sale
            //saleDTOS.stream().forEach(saleDTO -> System.out.println(saleDTO));
            //System.out.println(saleDTOS.size());



    }

    // создаем из узла документа объект Sale
    private  SaleDTO getSale(Node node)  {
        SaleDTO saleDTO = new SaleDTO();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            saleDTO.setCard_number(Long.parseLong(getTagValue("CARD_NUMBER", element)));
            //переводим дату в нужный формат
            long timeStamp = Long.parseLong(getTagValue("DATE", element));
            //переводим дату в нужный формат
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new java.util.Date((long)timeStamp);
            String strtime =  sdf.format(date);
            Date time = null;
            try {
                time = sdf.parse(strtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            saleDTO.setDate(time);
            //получим список продуктов
            saleDTO.setProducts(getAllProducts( element));
        }

        return saleDTO;
    }

    // получаем значение элемента по указанному тегу
    private  String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    //получение списка продуктов
    private  List<ProductDTO> getAllProducts(Element element){
        List<ProductDTO> productDTOS = new ArrayList<>();

        //у элемента SALE получаем PRODUCTS и его "детей"
        NodeList productsList =  element.getElementsByTagName("PRODUCTS").item(0).getChildNodes();
        Stream<Node> nodeStream = IntStream.range(0, productsList.getLength()).mapToObj(productsList::item);
        //получаем конкретный продукт и стримим в лист
        productDTOS = nodeStream.map(node->getProduct(node)).collect(Collectors.toList());
        return productDTOS;
    }
    //получаем значения элеметов тэгов у ноды PRODUCT, формируем объект и возвращаем его
    private  ProductDTO getProduct(Node node){
        ProductDTO productDTO = new ProductDTO();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            //создаем продукт из полученных значений
            //надо проверку перед парсингом
            productDTO.setProduct_code(Long.parseLong(getTagValue("PRODUCT_CODE", element)));
            productDTO.setName(getTagValue("NAME", element));
            productDTO.setPrice(Double.parseDouble(getTagValue("PRICE",element).replace(",",".")));
            productDTO.setCount(Double.parseDouble(getTagValue("COUNT",element).replace(",",".")));
        }

        return productDTO;
    }
}
