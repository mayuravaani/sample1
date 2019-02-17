/*
 *  Copyright (c) 2019 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.extension.siddhi.io.hl7.sink;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.conf.check.DefaultValidator;
import ca.uhn.hl7v2.conf.parser.ProfileParser;
import ca.uhn.hl7v2.conf.spec.RuntimeProfile;
import ca.uhn.hl7v2.conf.store.CodeStore;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import org.wso2.extension.siddhi.io.hl7.source.ProfileGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class okja {

    public static void main(String[] args) throws Exception {

        try {
            //ProfileGroup
            InputStream in = new FileInputStream("/home/mayuravaani/conf/src/main/resources/profiles/okja.xml");
            //in = new URL("/profiles/okja.xml").openStream();
            // Load the conformance profile
            ProfileParser ourProfileParser = new ProfileParser(false);
            //RuntimeProfile ourConformanceProfile = ourProfileParser.parseClasspath("profiles/okja.xml");
            //("/home/mayuravaani/conf/src/main/resources/profiles/okja.xml");
            String dd =streamToString(in);
            // Reader dd = new BufferedReader(new FileReader("/home/mayuravaani/conf/src/main/resources/profiles/okja.xml"));
            RuntimeProfile ourConformanceProfile = ourProfileParser.parse(dd);
            PipeParser pipeParser = new PipeParser();
            //Read a test non-conformant HL7 from file. See my GitHub page for this file
            // String message = readHl7FileDataAsString("C:\\HL7TestInputFiles\\FileWithNonConformingAdtA01Message.txt");
            String message = "MSH|^~\\&|SENDING_APPLICATION|SENDING_FACILITY|RECEIVING_APPLICATION|RECEIVING_FACILITY|20110613083617||ADT^A01|934576120110613083617|P|2.3||||\r" +
                    "EVN|A01|20110613083617|||\r" +
                    "PID|1||135769||MOUSE^MICKEY^||19281118|M|||123 Main St.^^Lake Buena Vista^FL^32830||(407)939-1289^^^theMainMouse@disney.com|||||1719|99999999||||||||||||||||||||\r" +
                    "PV1|1|O|||||^^^^^^^^|^^^^^^^^\r";
            //parse the HL7 message from the file data
            Terser t = new Terser(pipeParser.parse(message));
            String eventType = t.get("/MSH-9-1");
            String eventTrigger = t.get("/MSH-9-2");
            ProfileGroup.Entry profileEntry = ProfileGroup.getProfileForMessage(eventType,eventTrigger) ;
            DefaultValidator validator = new DefaultValidator();
            CodeStore codeStore;
            //validator.validate(pipeParser.parse(message), );
            if(profileEntry.getTablesId() != null) {
                validator.setCodeStore(AbstractConnection.getController().getTableFileList().getTableFile(profileEntry.getTablesId()));
            }
            ADT_A01 msg = (ADT_A01) pipeParser.parse(message);

            // Validate the HL7 message using the sample HL7 conformance profile I have provided. See my GitHub page for the XML file
            //HL7Exception[] errors = new DefaultValidator().validate(msg, ourConformanceProfile.getMessage());
            HL7Exception[] probs  = validator.validate(pipeParser.parse(message), ourConformanceProfile.getMessage());

            // Display all the validation errors that are generated.
            System.out.println("The following validation errors were found during message validation:");

            for (HL7Exception hl7Exception : probs) {
                System.out.println(hl7Exception);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            File f = new File("/home/mayuravaani/conf/src/main/resources/profiles/okja.xml");
           // File f = new File(args[0]);
            @SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(new FileReader(f));
            char[] cbuf = new char[(int) f.length()];
            in.read(cbuf, 0, (int) f.length());
            String xml = String.valueOf(cbuf);
            // System.out.println(xml);

            ProfileParser pp = new ProfileParser(true);
            pp.parse(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }



    public static String readHl7FileDataAsString(String fileName) throws Exception
    {
        String data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static String streamToString(InputStream in) throws IOException {
        byte[] buff = new byte[10240];
        int i;
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(10240);
        while ((i = in.read(buff)) > 0) {
            byteOut.write(buff, 0, i);
        }
        byteOut.close();
        return byteOut.toString();
    }
}