# API Docs - v1.0.0-SNAPSHOT

## Sink

### hl7 *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#sink">(Sink)</a>*

<p style="word-wrap: break-word">The hl7 sink published the hl7 message using MLLP protocol </p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
@sink(type="hl7", uri="<STRING>", hl7.encoding="<STRING>", hl7.ack.encoding="<STRING>", charset="<STRING>", tls.enabled="<BOOL>", tls.keystore.filepath="<STRING>", tls.keystore.passphrase="<STRING>", hl7.timeout="<INT>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">uri</td>
        <td style="vertical-align: top; word-wrap: break-word">uri name</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.encoding</td>
        <td style="vertical-align: top; word-wrap: break-word">Encoding method of hl7</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.ack.encoding</td>
        <td style="vertical-align: top; word-wrap: break-word">Encoding method of hl7 to log the acknowledgment message</td>
        <td style="vertical-align: top">ER7</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">charset</td>
        <td style="vertical-align: top; word-wrap: break-word">Character encoding method</td>
        <td style="vertical-align: top">UTF-8</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.enabled</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.keystore.filepath</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">${carbon.home}/resources/security/client-truststore.jks</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.keystore.passphrase</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">wso2carbon</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">Sets the time that the initiator will wait for a response for a given message before timing out and throwing an exception</td>
        <td style="vertical-align: top">10000</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@App:name('Hl7TestAppForER7') 
@sink(type = 'hl7', 
host.name = 'localhost', 
port = 1080, 
hl7.encoding = 'er7', 
@map(type = 'text')) 
define stream hl7stream(payload string);

```
<p style="word-wrap: break-word">This publishes the HL7 messages in ER7 format using MLLP protocol and receives and logs the acknowledgement message.<br>&nbsp;</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
@App:name('Hl7TestAppForXML') 
@sink(type = 'hl7', 
host.name = 'localhost',
port = 1080, 
hl7.encoding = 'xml', 
@map(type = 'xml', enclosing.element="<ADT_A01  xmlns='urn:hl7-org:v2xml'>", @payload('<MSH><MSH.1>{{MSH1}}</MSH.1><MSH.2>{{MSH2}}</MSH.2><MSH.3><HD.1>{{MSH3HD1}}</HD.1></MSH.3><MSH.4><HD.1>{{MSH4HD1}}</HD.1></MSH.4><MSH.5><HD.1>{{MSH5HD1}}</HD.1></MSH.5><MSH.6><HD.1>{{MSH6HD1}}</HD.1></MSH.6><MSH.7>{{MSH7}}</MSH.7><MSH.8>{{MSH8}}</MSH.8><MSH.9><CM_MSG.1>{{CM_MSG1}}</CM_MSG.1><CM_MSG.2>{{CM_MSG2}}</CM_MSG.2></MSH.9><MSH.10>{{MSH10}}</MSH.10><MSH.11>{{MSH11}}</MSH.11><MSH.12>{{MSH12}}</MSH.12></MSH>'))) define stream hl7stream(MSH1 string, MSH2 string, MSH3HD1 string, MSH4HD1 string, MSH5HD1 string, MSH6HD1 string, MSH7 string, MSH8 string, CM_MSG1 string, CM_MSG2 string,MSH10 string,MSH11 string, MSH12 string);

```
<p style="word-wrap: break-word">This publishes the HL7 messages in XML format using MLLP protocol and receives and logs the acknowledgement message.<br>&nbsp;</p>

## Source

### hl7 *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#source">(Source)</a>*

<p style="word-wrap: break-word">The hl7 source consumes the hl7 message using MLLP protocol </p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
@source(type="hl7", port="<INT>", charset="<STRING>", hl7.encoding="<STRING>", hl7.ack.encoding="<STRING>", tls.enabled="<BOOL>", tls.keystore.filepath="<STRING>", tls.keystore.passphrase="<STRING>", hl7.server.timeout="<INT>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">port</td>
        <td style="vertical-align: top; word-wrap: break-word">This is the unique logical address used to establish the connection for the process.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">charset</td>
        <td style="vertical-align: top; word-wrap: break-word">Character encoding method</td>
        <td style="vertical-align: top">UTF-8</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.encoding</td>
        <td style="vertical-align: top; word-wrap: break-word">Encoding method of received hl7</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.ack.encoding</td>
        <td style="vertical-align: top; word-wrap: break-word">Encoding method of received hl7</td>
        <td style="vertical-align: top">ER7</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.enabled</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.keystore.filepath</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">${carbon.home}/resources/security/client-truststore.jks</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.keystore.passphrase</td>
        <td style="vertical-align: top; word-wrap: break-word">Transport layer security</td>
        <td style="vertical-align: top">wso2carbon</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">hl7.server.timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">how long the hl7 should wait for the thread to terminate.</td>
        <td style="vertical-align: top">6000</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@App:name ('Hl7TestAppForTextMapping') 
@source ( type = 'hl7', 
port = 1080, 
hl7.encoding = 'er7', 
@map(type = 'text', @payload("{{payload}}")))define stream hl7stream(payload string);

```
<p style="word-wrap: break-word">This receives the HL7 messages using the MLLP protocol and send the acknowledgement message to the client.<br>&nbsp;</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
@App:name ('Hl7TestAppForTextMapping') 
@source ( type = 'hl7', 
port = 1080, 
hl7.encoding = 'xml', 
@map (type = 'xml', namespaces = 'ns=urn:hl7-org:v2xml', @attributes(MSH10 = "ns:MSH/ns:MSH.10", MSH3HD1 = "ns:MSH/ns:MSH.3/ns:HD.1")))
define stream hl7stream (MSH10 string, MSH3HD1 string);

```
<p style="word-wrap: break-word">This receives the HL7 messages using the MLLP protocol and send the acknowledgement message to the client.<br>&nbsp;</p>

