/*
 * Copyright (C) 2018 CyberAgent, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devyk.aveditor.video.filter.gpuimage

import android.content.Context

/**
 * Mix ranges from 0.0 (only image 1) to 1.0 (only image 2), with 0.5 (half of either) as the normal level
 */
class GPUImageDissolveBlendFilter : GPUImageMixBlendFilter {

    constructor(context: Context) : super(context,DISSOLVE_BLEND_FRAGMENT_SHADER) {}

    constructor(context: Context,mix: Float) : super(context,DISSOLVE_BLEND_FRAGMENT_SHADER, mix) {}

    companion object {
        val DISSOLVE_BLEND_FRAGMENT_SHADER = "varying highp vec2 textureCoordinate;\n" +
                " varying highp vec2 textureCoordinate2;\n" +
                "\n" +
                " uniform sampler2D inputImageTexture;\n" +
                " uniform sampler2D inputImageTexture2;\n" +
                " uniform lowp float mixturePercent;\n" +
                " \n" +
                " void main()\n" +
                " {\n" +
                "    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
                "    lowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n" +
                "    \n" +
                "    gl_FragColor = mix(textureColor, textureColor2, mixturePercent);\n" +
                " }"
    }
}
