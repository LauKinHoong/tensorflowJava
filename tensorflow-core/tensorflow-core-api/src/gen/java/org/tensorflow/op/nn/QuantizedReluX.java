/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Computes Quantized Rectified Linear X: `min(max(features, 0), max_value)`
 * 
 * @param <U> data type for {@code activations()} output
 */
@Operator(group = "nn")
public final class QuantizedReluX<U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizedReluX operation.
   * 
   * @param scope current scope
   * @param features 
   * @param maxValue 
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType 
   * @return a new instance of QuantizedReluX
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType> QuantizedReluX<U> create(Scope scope, Operand<? extends TType> features, Operand<TFloat32> maxValue, Operand<TFloat32> minFeatures, Operand<TFloat32> maxFeatures, Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedReluX", scope.makeOpName("QuantizedReluX"));
    opBuilder.addInput(features.asOutput());
    opBuilder.addInput(maxValue.asOutput());
    opBuilder.addInput(minFeatures.asOutput());
    opBuilder.addInput(maxFeatures.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new QuantizedReluX<U>(opBuilder.build());
  }
  
  /**
   * Has the same output shape as "features".
   */
  public Output<U> activations() {
    return activations;
  }
  
  /**
   * The float value that the lowest quantized value represents.
   */
  public Output<TFloat32> minActivations() {
    return minActivations;
  }
  
  /**
   * The float value that the highest quantized value represents.
   */
  public Output<TFloat32> maxActivations() {
    return maxActivations;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedReluX";
  
  private Output<U> activations;
  private Output<TFloat32> minActivations;
  private Output<TFloat32> maxActivations;
  
  private QuantizedReluX(Operation operation) {
    super(operation);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
    minActivations = operation.output(outputIdx++);
    maxActivations = operation.output(outputIdx++);
  }
}
