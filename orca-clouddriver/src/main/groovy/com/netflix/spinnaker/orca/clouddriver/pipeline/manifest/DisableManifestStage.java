/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.netflix.spinnaker.orca.clouddriver.pipeline.manifest;

import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder;
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode;
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution;
import com.netflix.spinnaker.orca.clouddriver.tasks.MonitorKatoTask;
import com.netflix.spinnaker.orca.clouddriver.tasks.manifest.DisableManifestTask;
import com.netflix.spinnaker.orca.clouddriver.tasks.manifest.ResolveTargetManifestTask;
import com.netflix.spinnaker.orca.clouddriver.tasks.manifest.WaitForManifestStableTask;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class DisableManifestStage implements StageDefinitionBuilder {
  public static final String PIPELINE_CONFIG_TYPE = "disableManifest";

  @Override
  public void taskGraph(@Nonnull StageExecution stage, @Nonnull TaskNode.Builder builder) {
    builder
        .withTask(ResolveTargetManifestTask.TASK_NAME, ResolveTargetManifestTask.class)
        .withTask(DisableManifestTask.TASK_NAME, DisableManifestTask.class)
        .withTask("monitorDisable", MonitorKatoTask.class)
        .withTask(WaitForManifestStableTask.TASK_NAME, WaitForManifestStableTask.class);
  }
}
