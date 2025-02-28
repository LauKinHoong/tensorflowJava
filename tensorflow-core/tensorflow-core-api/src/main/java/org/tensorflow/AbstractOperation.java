/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
// To test maven build
package org.tensorflow;

import org.bytedeco.javacpp.Pointer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Base class for {@link Operation} implementations.
 *
 * <p>As opposed to {@link Operation} itself, this class is package private and therefore its usage
 * is limited to internal purposes only.
 */
abstract class AbstractOperation implements Operation {

  @Override
  public Output<?>[] outputList(int idx, int length) {
    Output<?>[] outputs = new Output<?>[length];
    for (int i = 0; i < length; ++i) {
      outputs[i] = output(idx + i);
    }
    return outputs;
  }

  @Override
  public <T extends TType> Output<T> output(int idx) {
    if (getUnsafeNativeHandle(idx) != null && !getUnsafeNativeHandle(idx).isNull()) {
      int numOutputs = this.numOutputs();
      if (idx >= numOutputs) {
        throw new IndexOutOfBoundsException(
            "Can't get output with index " + idx + ", this op only has " + numOutputs + " outputs.");
      }

      if (idx < 0) {
        throw new IndexOutOfBoundsException("Can't get output with index < 0.");
      }
    }
    return new Output<>(this, idx);
  }

  @Override
  public String toString() {
    return String.format("<%s '%s'>", type(), name());
  }

  /**
   * Returns the native handle of the {@code outputIdx}th output of this operation.
   *
   * <p>The nature of the returned value varies depending on current the execution environment.
   *
   * <ul>
   *   <li>In eager mode, the value is a handle to the tensor returned at this output.
   *   <li>In graph mode, the value is a handle to the operation itself, which should be paired with
   *       the index of the output when calling the native layer.
   * </ul>
   *
   * @param outputIdx index of the output in this operation
   * @return a native handle, see method description for more details
   */
  abstract Pointer getUnsafeNativeHandle(int outputIdx);

  /**
   * Returns the shape of the tensor of the {@code outputIdx}th output of this operation.
   *
   * @param outputIdx index of the output of this operation
   * @return output tensor shape
   */
  abstract Shape shape(int outputIdx);

  /**
   * Returns the datatype of the tensor of the {@code outputIdx}th output of this operation.
   *
   * @param outputIdx index of the output of this operation
   * @return output tensor datatype
   */
  abstract DataType dtype(int outputIdx);

  /**
   * Returns the tensor of the {@code outputIdx}th output of this operation.
   *
   * <p>This is only supported in an eager execution environment.
   *
   * @param outputIdx index of the output of this operation
   * @return output tensor
   */
  abstract Tensor tensor(int outputIdx);
}
