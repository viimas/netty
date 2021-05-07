/*
 * Copyright 2021 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.netty.handler.codec.compression;

import com.aayushatharva.brotli4j.encoder.BrotliOutputStream;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assume;
import org.junit.BeforeClass;

import java.io.ByteArrayOutputStream;

public class BrotliDecoderTest extends AbstractDecoderTest {

  public BrotliDecoderTest() throws Exception {
  }

  @BeforeClass
  public static void assumeBrotli() throws Throwable {
//    Assume.assumeTrue("Brotli support is not available on this platform", Brotli.isAvailable());
    Brotli.ensureAvailability();
  }

  @Override
  protected byte[] compress(byte[] data) throws Exception {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    BrotliOutputStream brotliOs = new BrotliOutputStream(os);
    brotliOs.write(data);
    brotliOs.close();

    return os.toByteArray();
  }

  @Override
  public void initChannel() {
    channel = new EmbeddedChannel(new BrotliDecoder());
  }
}
