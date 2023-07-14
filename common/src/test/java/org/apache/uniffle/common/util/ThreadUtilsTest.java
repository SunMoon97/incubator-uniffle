/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.uniffle.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreadUtilsTest {

  @Test
  public void shutdownThreadPoolTest() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    AtomicBoolean finished = new AtomicBoolean(false);
    executorService.submit(
        () -> {
          try {
            Thread.sleep(100000);
          } catch (InterruptedException interruptedException) {
            // ignore
          } finally {
            finished.set(true);
          }
        });
    ThreadUtils.shutdownThreadPool(executorService, 1);
    assertTrue(finished.get());
    assertTrue(executorService.isShutdown());
  }
}
