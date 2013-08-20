package org.mbari.odss.services.planning.trex

object someJsons {

  val enumJson =
    """
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    }
    """.stripMargin


  var boolJson =
    """
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |
    """.stripMargin



  var dateJson =
    """
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:52:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |
  """.stripMargin


  var durationJson =
    """
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |
  """.stripMargin



  var floatJson =
    """
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |
  """.stripMargin




  var variableListJson =
    """
      |                [
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    },
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "square"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "path"
      |                    },
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:52:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:53:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "end"
      |                    },
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |                ]
      |
    """.stripMargin



  var goalJson =
    """
      |            {
      |                "on": "dorado_survey",
      |                "pred": "Track",
      |                "Variable":
      |                [
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    },
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "square"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "path"
      |                    },
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:52:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:53:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "end"
      |                    },
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |                ]
      |            }
      |
    """.stripMargin




  val goalsJson =
    """
      |{
      |    "goals":
      |    [
      |        {
      |            "id": "0x95c2420",
      |            "href": "/rest/goal/0x95c2420",
      |            "Goal":
      |            {
      |                "on": "dorado_survey",
      |                "pred": "Track",
      |                "Variable":
      |                [
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    },
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "square"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "path"
      |                    },
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:52:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 15:53:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "end"
      |                    },
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |                ]
      |            }
      |        },
      |        {
      |            "id": "0xb0425cb0",
      |            "href": "/rest/goal/0xb0425cb0",
      |            "Goal":
      |            {
      |                "on": "dorado_survey",
      |                "pred": "Track",
      |                "Variable":
      |                [
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    },
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "square"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "path"
      |                    },
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 16:17:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 16:18:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "end"
      |                    },
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |                ]
      |            }
      |        },
      |        {
      |            "id": "0xb0426a28",
      |            "href": "/rest/goal/0xb0426a28",
      |            "Goal":
      |            {
      |                "on": "dorado_survey",
      |                "pred": "Track",
      |                "Variable":
      |                [
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "spotSim"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "drifter"
      |                    },
      |                    {
      |                        "bool":
      |                        {
      |                            "value": "1"
      |                        },
      |                        "type": "bool",
      |                        "name": "lagrangian"
      |                    },
      |                    {
      |                        "enum":
      |                        {
      |                            "elem":
      |                            [
      |                                {
      |                                    "value": "square"
      |                                }
      |                            ]
      |                        },
      |                        "type": "enum",
      |                        "name": "path"
      |                    },
      |                    {
      |                        "float":
      |                        {
      |                            "value": "1000.0000000000000000"
      |                        },
      |                        "type": "float",
      |                        "name": "size"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 16:13:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "start"
      |                    },
      |                    {
      |                        "date":
      |                        {
      |                            "min": "2013-May-16 16:14:01.566673"
      |                        },
      |                        "type": "date",
      |                        "name": "end"
      |                    },
      |                    {
      |                        "duration":
      |                        {
      |                            "min": "00:01:00",
      |                            "max": "18:00:00"
      |                        },
      |                        "type": "duration",
      |                        "name": "duration"
      |                    }
      |                ]
      |            }
      |        }
      |    ]
      |}
    """.stripMargin
}
