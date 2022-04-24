/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.sort.protocol.node.extract;

import org.apache.inlong.sort.formats.common.IntFormatInfo;
import org.apache.inlong.sort.formats.common.StringFormatInfo;
import org.apache.inlong.sort.protocol.FieldInfo;
import org.apache.inlong.sort.protocol.enums.ScanStartupMode;
import org.apache.inlong.sort.protocol.node.Node;
import org.apache.inlong.sort.protocol.node.NodeBaseTest;
import org.apache.inlong.sort.protocol.node.format.CsvFormat;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for {@link KafkaExtractNode}
 */
public class KafkaExtractNodeTest extends NodeBaseTest {

    @Override
    public Node getNode() {
        List<FieldInfo> fields = Arrays.asList(
                new FieldInfo("name", new StringFormatInfo()),
                new FieldInfo("age", new IntFormatInfo()));
        return new KafkaExtractNode("1", "kafka_input", fields, null, null, "workerCsv",
                "localhost:9092", new CsvFormat(), ScanStartupMode.EARLIEST_OFFSET, null);
    }

    @Override
    public String getExpectSerializeStr() {
        return "{\"type\":\"kafkaExtract\",\"id\":\"1\",\"name\":\"kafka_input\",\"fields\":[{\"type\":\"base\","
                + "\"name\":\"name\",\"formatInfo\":{\"type\":\"string\"}},{\"type\":\"base\",\"name\":\"age\","
                + "\"formatInfo\":{\"type\":\"int\"}}],\"topic\":\"workerCsv\","
                + "\"bootstrapServers\":\"localhost:9092\",\"format\":{\"type\":\"csvFormat\",\"fieldDelimiter\":\","
                + "\",\"disableQuoteCharacter\":false,\"quoteCharacter\":\"\\\"\",\"allowComments\":false,"
                + "\"ignoreParseErrors\":false,\"arrayElementDelimiter\":\";\",\"escapeCharacter\":null,"
                + "\"nullLiteral\":null},\"scanStartupMode\":\"EARLIEST_OFFSET\"}";
    }
}