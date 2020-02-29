package com.wq.springboot.utils;


import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.poi.ss.formula.functions.T;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.*;


/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/2/18 10:09
 * @Version 1.0
 */
public class ExcelPicture {


    private  static String image = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAoHBwgHBgoICAgLCgoLDhgQDg0NDh0VFhEYIx8lJCIf\n" +
            "IiEmKzcvJik0KSEiMEExNDk7Pj4+JS5ESUM8SDc9Pjv/2wBDAQoLCw4NDhwQEBw7KCIoOzs7Ozs7\n" +
            "Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozv/wAARCAKAAoADASIA\n" +
            "AhEBAxEB/8QAHAABAAEFAQEAAAAAAAAAAAAAAAIDBAUGBwgB/8QAOhAAAgEDAgQFAwIFAwQCAwAA\n" +
            "AAIDBAUSBiIHEzJCARQjUmIVM3IRkiEkMVGCNEOiF0FTsmHwNXOD/8QAGgEBAQADAQEAAAAAAAAA\n" +
            "AAAAAAIDBAUBBv/EACURAQEAAgMBAAICAgMBAAAAAAACAxIEEyIyFEIBUiMzBRFiMf/aAAwDAQAC\n" +
            "EQMRAD8A7MAAB8/sffAAD5/Y+gAfP7H0AD5/Y+gAfP7H0AD5/b9P6H3wAAAAAPAAD5/Y+gAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAixFT5NIscbO3acw1FxTW2XHy0faB1DpIrkvUafp/iBb7xGqyS4yMbdHIrLlG\n" +
            "ysoEslJLl3EclYl1bQKgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB\n" +
            "HI+MfSdgPmR9AHzIZHwHnofchkfMiJ6J5D9SjzV9xBquNe4xd0y91pc5DIsmr417ii11jVSfysb3\n" +
            "rpk8hkYT65uItfMTHXMxsnTTO5DIwX1wqQ3fITzMZ00zOQyMb9TVu4mtxj9xX5eNPXS/yGRaLWxN\n" +
            "3FVZ1buMk5ppGtK36j9SG33DIyp9J5DI+DEPUiREFAAFAkARAkCIApVUfOppI/cp5y4gabuFBeJJ\n" +
            "2jZl+J6SYx1ytVNc4mhqI1b/ABA8pU81XTSZRs0bKdM4e8Qq1atbfWNtLPihpKksz+Zp9potnmkS\n" +
            "6QtH7lA9ZQycyNZV6WUl1SGOsbNJZ4fdipkV27V6gKwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAABDLdiBMEGY+cwCoCmsmRLcB88T4fGY+k6pfB4+H6lKSZY13MWE13iXaYqzTTJ\n" +
            "OPZk+YqlGStiXuMDUXOeTpLNpJJOpjRyc/VsTxaZua5qvSWMl3k7SwyJbTS/Nqm1OBcNXyMWskk7\n" +
            "dxIkY6ybMszMqa81upiWLe4kCddVI4qBiSJ8p2U8VJYjECdTZHFvcSxb3AkVrsqUVaRe4rLWzqvU\n" +
            "UwxU5NU1M0uo7rIpfQ3de4w4xUyTzaljrjtkhuMUncXCyRt3Go7l6StDWzxtuN2ea1q4ralkyJGG\n" +
            "p7uvSxkI6yJuljanNs16x6rs+KQy27T6rGzswpkinkMilKhEhky9R9WTLpASLkpHd2ksj5Gu3aBx\n" +
            "zjQrL4fE5XZ6hKa4RSydKsp6M11paPUFsZcfUPO94s1XZa1oJlxA9L6XvFFdbZHJTyLtXHEzKtux\n" +
            "XqPNOidWT2G4x5SNy2bE9HW2viuNFHPC2WSgZAEFY+5ASBDdiTAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAU2XIqFKRsdzdIHzp2qUpq2KmXKZlU1LVWvqSywMsLK0xx+6cQrvcZ29X0w\n" +
            "O+Takt8LYtPH+4uI71b5lyWdf3HlmavrppMpJ5P3FxT3q5U3252/cB6ojq4JFyVlMbWXdYWxY4nZ\n" +
            "+JddTssFQ206VZ7nBd6RZMtxocuqlsYcey+krZKluootGrbgysrfEkcSc1bOjjx6oripLHIipIyV\n" +
            "6ZNkRiSGRPlOwMQCQAJEEzqiCQL1k2lEADWTaQAkQVOyIJAsRIlQjiV5NjpI5KxJVyItiYus7Nkc\n" +
            "cukqc6RelhtVSK5GzOTVNTsylDdW6WMstXHy8slU1eaaOgpmnkbE5rqjXs/MaKlkOjx8m1NDNj1n\n" +
            "Z2qa+UNMvqTr+4t11Na5Gx56/uPNcmoLlVs3MnbH8i1WtrmbbO37jpy1XqyluNNUr6Mqt/kXCseY\n" +
            "bbrO72hlZZMl+R1LR/E+KvxirmxYodOxXIKW9PNHVxrPC2SlxkSGPuNH4gaSgutuknhi9ZTeMiE0\n" +
            "ayRsvuUDyJUU70dWySbWVjs3CHUTVEPkZm3Gm8ULEtrv7Oq+mymJ0Pemst8jbLFWA9QZEuox1tud\n" +
            "NW0kbLKrZL7i/XqAkpIhluJgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEQATQixH4qTINt6\n" +
            "SpS+tjjuNA11rqC0U0lNTyesZTXGq4NP2xt3qMcL8pctV3Fp9zKzE+aVqxNdX1N1q2nmZmyJU9qq\n" +
            "5t0cTY/idIsvDvFVaoU3Clsdvhj5XKXaambkzjbGPC4mulrk3+0U6iw1tMv2mO+R01Mq48pf2lNr\n" +
            "ZRSfciU15/5Fk/Eed5oZI/uLibBpXU09sqVVm2nRrxoOmrVZoVOa3jS9bbpGZYtqm13TmnVj6+un\n" +
            "arbXwXWiWRW3FRV3HM+H+olhqfKVTMp1RlWSPmRnIzcfp9N3Hm2UwpFeoky4mpNdjJtqAAtSIJAA\n" +
            "ASUCIJAJRxGIAAkRAUkRBECREkRABVGJLpKk22U2XcTZlp4Gnm7ScMeTZGn6+1EtFC0Ebbhjms1M\n" +
            "dZtWt661jLVyNTQttOfrzZpNuTMZSjtlXeqvJVZlZjolh4exw4yVCndmp4+NodfZTmtLZa2pbbE3\n" +
            "7S8bSlwXcsR3Cnstvpl2xKVvL03/AIl/aatc/VU8d5/qLHcIF9SP/iWfr0rZLkrKehprVb6va0am\n" +
            "t3jQNNNk0KmbHzdvpNcdj+HfECSORbfWNtOx0skdRAssbZKx5su2mbhZ6vnwqyqp0jhnrHnxrb6x\n" +
            "vU+Ru7TUsOurqHdiBtZdpEj5Y2i8TNMteLS08K5SKcDqIZKSpZZlZWU9bctWVo23ZGj6k4aUN2ya\n" +
            "NcZGMv0pxW061u1pxWnlyX5HXNA8RGvk60VV9zHtNNruD91hn/l1XH8jbuH/AA9nsdT5uo6gOnR4\n" +
            "9pUKULfqpVAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIgAJR8SzuFWtJTNIXjdBZ1kK1cDR\n" +
            "SdJNVoqZclulsrdY3r1P9OpuFtsdvstMsca7i8Vaa1MyxruLVmaRsji8vkVr5b2HGrSVLdpTb1Nz\n" +
            "BVBz/VfTdmdTcOrqBIr5VsLJJH09IrKKmuMfLZVDEl9yic2tbMeSdpcv1RpCe1V/m6Fdpu2kbv52\n" +
            "gWCT7imamWCvhaOZdxiaWy/TqnKHpNjJm7p1a2HHrTKybWxPvdifY/cxBfuZGvM9bbySkAAkJAAR\n" +
            "JAAAAEAAAAALAAAIkgBFR1MGX2ko2VVJr/HSpU7hWrQUDN3YnJWtldqq+NkrcvI6ZcKCWv8AS7S6\n" +
            "tdBSWqPavqG9jzTjlqZMey3sen6S0UiriuRkGkyXaRZmZiKriatVWamzM6yivyG7IkCddVKeO7JS\n" +
            "os0ihiKjb+oqTU1JcY2iqFXcaHeNKVdlua1tD05G8fiVoauOb0qhclNrj5qmtWpkx7L7S9e1XbV5\n" +
            "33DN9u0x1DDBjlD0mSXpO/NTUtGpfO7Jeo+8vdkSxJGP5SpSR5LtUkqsvT0lUGUU1jxKgAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAB8BDxb9PA+ZeJO6dlUEchkUpIEMhkRsPoI5HzIVSSTpMRcK/bjH1F\n" +
            "a5VvLjxXqNdyZpMmNHNmluYcNUL6jZSFQj+JI423pv8AyiSVQSyFVQYgAif/AEGWI6dwDbjLrJ+w\n" +
            "vuHM3YsSBKhiTKuO0iFI2TrQABsJAiSLQESQAYjEABiMSREAAAsAAY9giSIkbMiPSFUlkC59fSaR\n" +
            "Zm7STY4jaRx9onVkkxyIksQKqp+WPb0AAn6+VIkWKgK+VKeO0bccSWIXqMn67G0rq21fl2xY2CGR\n" +
            "WXaanIuW4yFtrWX0mM3F5FbNLJhpsgKMcmXSVDvfTRrymfSkrKTyPJrYSBTyJZFiQIhQJAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAACLEciTdJECQIAlOyZE+/qR2jaQ/Ut6yqWmjJySKq5GJrJFqNuRqZsn9Wb\n" +
            "Dj2Y2qmaokyKax5F4sK44qykWhaM4lbVTpzXWt8cRiXSwtIuQWmZiuupV2St8RiXXlGHlGGtJ7Fv\n" +
            "yxyy48sxHyzDWjsUcRiVuWykMSfSp9IYjEqEVGxqjiMioRxG0p1pEkMSWI8p9IgkCXqIxJAAASKF\n" +
            "MkAAAGIl7sEciWI7iaomUQSYRw5dZWOSlPIY5Fw0cXuULDl0sTtSVuyhVKjQyKw5LMV6VORFlI4l\n" +
            "ZYWJeWYrWlVUrfEjiXXlmItTN7RrSexRxI4lx5ZSPLZe0x+jsW+LEWVi4ZRiT6pU1K3CtjuK3LyD\n" +
            "QmSZ6ymWtdarLixlfDd/A1SFWhbIztFV8xcTr8bNs52TCvyX6ESX6nRa2p+pAD9DFU082TCgKVKk\n" +
            "gAWAAAAAAAAAAAAAAAAAAAAAAAAIePSfD74nwlL4fWxXcR7SzrKtY48SKyTKplOoqkiUsJK1pC1V\n" +
            "ZKmT4lvqKrW0W5m7sTm5Nq+W1MzLH3zVq0EbLzdxodVrqraRsW2mu3WvmudazM23IoyQ7domP7N3\n" +
            "HOraqHX09NMvMbadI03qKkusK5MuRwtqZWXcXFnudTaLirKzcszTMsWbHVPSS00an1aZV6VMVpm8\n" +
            "LdbdG2W4zjG1OOXP2qVPkr7RyV9pUBWsm1KfJX2jy6+0qEhrJstWpF9pTaiVi78T5+pPTjpPdUse\n" +
            "1sVijJbMW2mXPpjriyyTyKa/JRMpRaORTY+WrFOSlRzXrhM08pr2WI6jLTWxW6THyUEkPSaFcepb\n" +
            "c5ppRVWHSMmyxYkph11VsiCWIxDEiCQAiSAAiwUkwJXKOIbaMipDTNM2Jkx49ismptVcm6TXdQat\n" +
            "goFZY23GR1dcY7Ra2VW3HFKirnuNWzM203ZwavJ9Nkm1zVMzYsXVt4gTw1K85tpqPLUjNT8yP5Ge\n" +
            "f+2WZ3d9sd6or1CrKy5GdWmiVccTzzpe+T2ivXKVscjvdnr1r6KORW7TPOrnciale8lVUlyFJdxI\n" +
            "zay1tqU+Qo8uvtKhI91le1LfyykWpFYuARrJtSzagUptblMgDHWGdtldlMLJbmXqLWSklU2PHIg0\n" +
            "KsY8nH2ZJzNdxbHEqUs3l23GYakUtai3ZdJqdNY2bsmlanr4pO4vFZW6TXZKKSHcpcUte0DYyGea\n" +
            "qfpr1LOYnzIpQ1KyLkVDoTU0w6pH2PpPh9Ua+hMAFgAAAAAAAAAAAAAAAAAAAAAAACDEGYn4kAiv\n" +
            "K3qpOXDkYNmarkMrdZ+XB+hbW+FWbI5Gbbs1buPXr2ZCnpljjOf8UJJFpGU6OvSaRxEtjVtuZo1O\n" +
            "hr1z5YcNbV6cVp16situVdpaq3JqWjk2l02K9Jq5NnXmpoXcu7qKNRuZSt1MW65TVawr7iMf0VWs\n" +
            "uv8AC+VmpFVTo6mkcP7c1BQKzbTcuavuN+alyMk1VKoKfOX3EOcvuKqmPWlcFDzC+4eYX3E9hrSu\n" +
            "RxKfmF9xLnqNpVrSePgP4FPmJ7iS+I8vNZS8T6rHw+Fo9BFlVl3E8T4RrVfR8rGootuSmHmVo5MT\n" +
            "ZW6TEXCNV3HO5HH1bmHItV6CKlONsioc3XVtgACAAAGDbVGJFvaJFSjj5jGahpljXLuLO3x47jKd\n" +
            "p2ePjlq5qcj4mTSMzK3Sc7pV2nWOJ1lkmpGnhU5HDJirRttbIrNLNxaVmblsSVmZiS44kWxVcmNe\n" +
            "abc7StZl/mVx9x3Th7MzWxVkOH0sHnK2NY92479pGg8la48us28dNbkemzMFKfMVRz4/cbGzn60q\n" +
            "Ap+Zi9w8zF7iNjVUJFDzEZ958fuGytVQFPnL7hzF9w7JTqqEcdp8yUnkJqUoqSAL+lKLR5FjWW5W\n" +
            "XJTIrtDLzFMGTHOSVdjXoalqafFukz1O2UeRhqyFY2yMhb5OZGaHFr/JrLNknyvWJp0kFJqdhqpg\n" +
            "AKAAAAAAAAAAAAAAAAAAAAAAAAQfpILuUn49JFdoP1Yy8Q5IU7ayqpfV0fMiYwcbS08hys3mtm1j\n" +
            "9Tq2Jegt6qnjqYWjk7ixkr2KM1fKZq5c6sc8etnNdbaI5dS09GppMlFVx7WVjvzSLUR4zKrGLqNO\n" +
            "2+ZssTX/ACZpuTOriq0lXUNy1jb9puGldHS+ZWeoU3qGw0MO7FS+yWNcY1Ux1nZ1wsi0lOscfaU/\n" +
            "NyFvzCWRqdlMeqt5iUjzpSnkRxJ2yHlW50o50pTyBW2R74VFmkJeYlKJInbI81lU83IVFr5FKIMk\n" +
            "1klPWvFuZcR3FWMTy1GOJmnNSemWejq1buKyzKa2rMpW83IpnnnsFYWckmVVMXXTLJtLNqmViOOX\n" +
            "UYM3IqmTHjSVcSShVBp7UyAAKebI4kgAbBTbqKxRkDJMsrQSLiZBWUwFPNyys1e2O06OHNq1smLZ\n" +
            "f3Clgr6ZoJDimrNGT0la09Ou06s1fKzEZuXWrjMpkycuVYZ1cBaCrj2tE37SpHRVdayxrE37Ts02\n" +
            "m6KRsuUXFHYaGmbLlbjX/Jlt1TT9I6OWmkWpmXcdAaq5Maxx9pTZlXbGUVXFsjBWaqYPpcebkZSL\n" +
            "TMU8gY9sjLMzKXOlHOk9xTyA7MjxU5je4+c6T3FLIlkTtke7K3mJPcFq5C3ZgrYldlGs0vluLKVo\n" +
            "7qY3mKRMk5qTWGaZ1bijFSOrVjBKSWRl6TbnlsX4ktiWRWItNFGvUYHzcq9IkmZu4rHytZa/T6VK\n" +
            "6bmNipkLbC0cZjKWPmSbjPQrjEOPj7K2Vm8yqEk6Sn4eJU8DptSa2TABT0AAAAAAAAAAAAAAAAAA\n" +
            "AAAAABEiSIgU23KYO6YxsZ5uk1u8SZTHO5Utjj16UcslI5LkR6VI47jjaunVaqjN7T6sjHwDU2G5\n" +
            "jAp8wjzBKtdlYFvzBzDJqrpXS4ktpZ8wLMw66T1rxVJFutQVFmyJqaY+tUAVsiRPpj+USQA9GwAS\n" +
            "I1o2RGJJSWJllNUjyyWIAqpJoAClbbSipAASkxBIiAIkhiNlbaqbR5DlkhkTNKmtkeWpLEYgqpe1\n" +
            "QuS9RFmYluYiT5SipIZAK1RIlQjkqj0yTsKobaU2qCi02RWtMmtLjaRLfmEeYxknHTJ10uAW/MYc\n" +
            "waprDVLjEjuUprMVFmMNSnpoyYcxiW1htGqdaOYGmIsuIxyIyTspeW2b1jYF6DU4ZOXObLStlAp2\n" +
            "OFk1loc2VyT8CHh/QnH0nU/++mjM+UwAeqAAAAAAAAAAAAAAAAAAAAAAAAQ8f6ECfj/Qj4k/smvl\n" +
            "QqG5cDGrzScyRjYrk2MDGs9zHK5tenQ4UqgUA5lN39kWbEp5ZCQipUs0yAAzM+oBiAnUAAVqjiSV\n" +
            "sQMQnVWjm3F0rZGPVdxeU5jprZPpWJBiKkteklAUBCQIklAiCQDwBLIYgRJAAAABEEiOIEQpIAAA\n" +
            "HqIJAkUwSIqUIySYqWck5UqC1UyS3cYuRIkxFSmcBIB6iMQCXuqOJJdoBkNVRWKisW6laMw01qlJ\n" +
            "iKlRiJgphlFtu4z1pqeZHiYFlyMhZ2xkN/iUwcmdmxKpJSKklO3LmpAAsAAAAAAAAAAAAAAAAAAA\n" +
            "AAEGY+q2RTxx6SSgG3BgGJGHvE2K4GDUyl4bcY1T5/lV6dXjz5CShSRhr5ZJ+lNlI4lQYhk7FHEY\n" +
            "lbEYjZXYp4jEqYksSk9iniMSpiMQrsUcRiXHLyKnLCexbxx5Fwq4hVxJEsNVsEiKkgkUABjCQAAk\n" +
            "AABIBCIJACIJACJIACJEkAtEAAAASqUWIkgU8U5I8lLNocWL4NHkGfHTHksSs0Y5ZTN2KIK3LHLC\n" +
            "uxRIlTlkuWTsdijiMStyxiVsnsU1UqKSxBKexFhkGIkyxjF5a5FWTFi1JUrY1Jmw1rRU7S2yPpJq\n" +
            "UIWyjUrr0nfx/LjV9JgAygAAAAAAAAAAAAAAAAARAkCnkMtu4CnJNAq5SMq/kfIZopVyjkVvxY49\n" +
            "xU1fU09b5SllxLHhrrir+prRVDZKwHc1bLwyDdJGOTmRKy9wkbaTR+zXrs3qFiXlw3TFqfN5vWR2\n" +
            "cPyKSIkia9eSfNIkgCQJESWIAkpHAkpQEgoCBQrAkqh4j3EgAhJQAAAAeJEiJIASIkggAAAAAAAA\n" +
            "AAAAiAIkgFogAEhEACIZiREPUQSDARAAWjiRJAlk2RAAESJIACJICfKqREO2YBWxbIr9j9Wy0v21\n" +
            "LxSwtrZQbi/U+hw/LjZPpMAGdIAAAAAAAAAAAAAAAAQbaTIsBHHuMbeLjFbrdJUyNjtMk205Xxg1\n" +
            "A1PRfT423dQHJtU3drxeJanL/vibXwnsMlbeVq2X01U0Olp3rKuOJd2THpTQen1sdnjjZfUbcBs8\n" +
            "a4xqvaRkXaxPcG6SaJ+mt3BfXLUurh9wtT53J/sdfH8hIiSMM/TIAAASIkglIABCSgKCgJKRJKGM\n" +
            "JKRJKAAAAkRJB4EgABIiSCAAAACQEQAAAAAj2gdoESREkFogACIAD0IkiIAMAwEQAFogAl6iAA9l\n" +
            "EABkGHaB2kCmFDBTKV8titf2TIqY61/ZMip3+P8A63IyfSYANljAAAAAAAAAAAAAAAACmxUINt3A\n" +
            "U2bKFmY86cVK2So1K0fbiejJFyU5LxM0LPX1DVdGuTAclstxW2V61OOWJ2Cw8WKapqY4ptu3E5TU\n" +
            "aUulMrNJA2K/ExKtyZlxyVlYD11S1MdXAs8bZKxVk+2xo3C28S3HTy8xssWxN6k+2TTyfprFwb1i\n" +
            "2K9wX1ikfO5P9jsY/oUkRUkYZ+mQI4kgoEu0ipJV3AJSIgKEKigKChIABjCREkAAAAkRJB4KSUip\n" +
            "JQBIiSCAAACREASIjIAARJARHaB2gCJIMFosAAIgAPQiSyIgAABEAErRAYB6iO0BQ9lFSTEQGQHa\n" +
            "GHaR+wpsO0kRMpXy2K0/ZMipjrX9kyKnf4/+tyMn0mADZYwAAAAAAAAAAAAAAAAiy5EgBSxZuoNG\n" +
            "rdSqylUAaxq5aSCxzSNHGu32nmKq3Vc3/wCxz0JxUrWpNPNu7jz3HjJWrl0swHdODsbR2Pd05HSM\n" +
            "slNR4dx0kFhjjWRcvyNubFtpNDXrl95lLJS+uy47e4s8sj53keadnD6kUkRUkYa8+iQizYklI1i4\n" +
            "02SiVJRyK3SDE2+tyqeWxmJNpVJFAbaoUlCooCgoCREBjSAAEgATs8CREkVtQNtJEV+QVctw2kSC\n" +
            "sR9Jl3ZCSRYVyZlVfkNar5YlTIYmBumsbXblbKXJviavUcV6aNvR6fxNrHx6pOzowOY/9XV/+qF4\n" +
            "tKzbv/UVwshs6diFNNtPES03FvWZlYsdUcRoKLKKhbJhPEo2b5JU00P3JVX/ACLVr5bVbdOv7jgd\n" +
            "y1XdK+Rmknx/FjFtcqtm3TyfuNqeF/ZNZHpaG40NR9udf3Fxtb7bKx5ppb/cKRvTnb9xt2neJdXR\n" +
            "SqlU2UZ5XC/qTkdm6SOW7Ew9j1JRXWm2yeoxmmyaPcc6sdS2JqVPqYljj1NiYXUGpqGxr6jep8Tm\n" +
            "t64n3CpkZaVl5ZmniZqTVS7E00CtulX9xT8zTf8Anj/cef5tW3aTc0v/ACKcepq5d3ObL8jZ/Apj\n" +
            "7noheU3SykThdv4gXSmkXJlxOgaf4hUlxxiqmxUw1xMkqnM3TIi20jG0cy82FslKm7/E0q2lsyiA\n" +
            "RKJAB3E/qyUFNpFXaVlXI1+5V/Lq+UJ9IZqPcG6sSNPuplYqd2RVeVogMCcc7KRJQrk2JElS/eKx\n" +
            "+q1K+WyUMeMJdqW1L9suU6T6PHOsuLX0mADK8AAAAAAAAAAAAAAAAAAAAAGg8ULK1x08yx5Mytke\n" +
            "eZIWp5GSRWVlb2nryaFZslkXJTS9QcNrXdY5JI48ZAOI2PV1daJFxkblqd40XrGm1JRKrN6xwfVW\n" +
            "mZdO17QMu0utA3lrbfY8mxjYkd8vmSybTG7W6TNVGNXRLJ8TBrkuSnB5s+nV4teVQkRBq/qyfskS\n" +
            "b1IWiKfcVI/uAabVVP068G4Usnm6RWNP1lRMsnPUzGj7mtXQcrLcZtdhmmXIEmyXcRy9ph/ZCSki\n" +
            "IyKEgI/kF3NtDxJQR6SnUVsFFG09RKqqTrtSdtVbEkqmk3LiRRUUjLGzMYn/AKqr/wDVNyeJVMVZ\n" +
            "JdOxGJy9eK65bv8A1Lyj4p00kmMn/qTXApPY6Flk2I6mxUxtt1BQ3dV8vIuXyMhNNFSRtPI2O018\n" +
            "nHqVVS3u11prLSNLM244/qjXlbcZ2ihkxjI681M1zq2gjk9NfaarRW6puEqx06MzMdvh8bz6a9Uo\n" +
            "zTTM3qSs3+RT8I2bpVmOmaf4S11TGs9Yq8tjebbwqs1NH6iLkbvyw7PPXJm/8TftDQzL1Rt4f4np\n" +
            "peHdkX/aLau4b2aaGT026Sdh5uWSSPpZlDMzbmZmM1qqyfQ7tJTY7TE08fMqo4/cyj/sZC06buF6\n" +
            "fGljb/I2qHg/fpIc8Y/3HW9C2amorLGyxrl+JtXLXEbDzJdOHt7tSs00atj7TW5IpYWxmjZf8T13\n" +
            "NRwVC4yxq3+JoGsuHNDcYJJ6WPGYpLh9rvFTbqlZYZG2nUrbxJiksrc5vU6Tld1tFTaqtoqpGXEs\n" +
            "cmXbltMVY5pkmmUvl9qbtUyNI23Ix8MMkzYrEzfipTjTORV9zHdOG+h6aG3LW1keUhl+Xjk9PpC6\n" +
            "VC5xwNj8lKzaGvarlyP+J6ZjpKZY9sMf7SS00OLZRR/tJ2pDyXWWuroZMZ4XX/EpQsyt6bMrKeoL\n" +
            "xpa23qmZZIFVvipwnWmjJ9O1rMq/y5X0Ng0PrXctBVMdOjbnR5RttPM9PM0EysrYsp2rh/fvqNsW\n" +
            "CZvUU5XLw/s3sORtwBE5TaCIYfiUKmXLpmZjn9RW+bvmJuV8q1orWzZbjm+n2atvzN2mScQ6lCuN\n" +
            "EqkSpjjAv4lNd25jHS0QSYiMfyoJUK5TFNukvLTTZSZMVx59MOStWw064xldSkvTipNcj6OXJr6V\n" +
            "ARGRb1IAAAAAAAAAAAAAAAAAAAABTxI5ZL6ZLJcSnjy49oHN+L1njqLP5tV9TI4dRs0NbGy9sino\n" +
            "fiViumGVurI86rtrP8jyfor5emrDU+d01HIvtLFvusS4e+ppZcipXLjUscnny3uFSIUiDnS3aVBG\n" +
            "2MhFegkrGKhj9TUnm6BsV3Gg6VuLWq7NBM23I6kyrNCy/E5HqqikoLtz127joYPUpdeWRZ6ZZVKZ\n" +
            "gdG3xa+gWJm3GwMuJrZJ1pACKkiQb7ZJV5aDqUdTB4p1lT5Kmadu1TiurtUT3OtkjhlblqblxI1I\n" +
            "1LC1JC245C0jNl7mY6vCxedmlmr0ZSN3ZEmjmXdh/wAToGgeHv1pVrKxW5J06PhlYoY8cWOnLV2e\n" +
            "b/LzNu5TftI4yeDf0bwPSbcN7J2xtiYLUnCqhmpmahVslUbKlxm23mrtlQrwytt+RuV814130+sE\n" +
            "bYyGj3S3S2qramm6lLVcukx9c0rZWp1aqqVj3MzMd+0DoymtVujnmjykbccz4Z6d+qXhZWXKNT0R\n" +
            "DHyKZYl7VMnykjjxjx2qSjyx3EWj3ZN1Eo8sdwH3cfe0+nxm2kmzg/GSmVb5zFXtOc0TY1sLfNTo\n" +
            "nGKrWS9ctfac8oFyrYV+alGz1FpJuZZIW+Kmd/QwWk4+XY4V+JnMiTZ9KbLk2LdJUIsBpGvNFQX6\n" +
            "gknhjxqFPPlzt89tqWgmXFlY9cdS4nOddcO/rkjVdOqrMBxWyU61NyhhbwybxZT1Ha6Vae3Qqu1e\n" +
            "Wpy7RvDCpoLitTWL0nW41VY1j9qjajZPpb4jp/EYsS6lKEfxNd1lYYr1ZZo2VclXI2NenaUplygk\n" +
            "VvaTtqfTyTXU7UtXJE3UrMbRw7uLU14VctpR4iUS0epZMV2sYnT1S1FdI5PkTknaVY61p6GZslVv\n" +
            "iG6S3t8nOpI2+JcHzeTzTqz8o9pUhXFcmIr1Frdq9aKiZsiZ+njT+IF328hWLXQNFlPzWNZvFa1z\n" +
            "uP8AkdG0XRcmiVjo1OsDZJm7Sn2kpOopqc6lgAE/KpRk3KZazqxiepjYrbDy4Mjc4U+mlyKZDFVP\n" +
            "itltIZYxszGr3LX1toKvyzSbjuNFtiriw6Szt9xgutIs9O2Sl4vQUKgAAAAAAAAAAAAAAAAAAAAC\n" +
            "mpIADU+I0fM01IeaWXGs/wD6HqfVlL5uxzJ8WPLVdG0NfMjdsjFSPRXDVlbSyt8jLXak7jS+Dt15\n" +
            "1p8ozbsjoVyhaSFjQ5WPZmw1rTX1b08SKkV9ORlYqY7jiV5dX6AqkiORKVSNsWxNZ1taPNUjSqps\n" +
            "je4lUQrV0jKxkx0OO6ductquPLZsdx16hrVr6RZFOR6qtzUVxaRVM9o3UWLLTSMbuX1JLoUi4sSy\n" +
            "yXElisirKpTVtxzJ80x/skvUG2xt+JGTpyUqL9iT8TNPqmTJ8uF6+q2qL834mFstE1xucMC+5TKa\n" +
            "3TC+yeHiWmlq1aG+QyP/AEZlU+ixz5cqnpbTtsW1WeGBV7VMuWlvkWaihZWy2qXfh/QpjkPki5L4\n" +
            "qTIsUmnI+I3Duputa1wt6r0nL49L3L6qtFyGy/E9Ufcbp2lq1qpmqefJBHl8VCmvaB0gum7evMX1\n" +
            "G3G5lGNVyyUrACLEimzYgGbaWddMtJSSMzbVXIvOldxz/ilqRbZZ2poW9RgOM60uv1a+ST5dO0pa\n" +
            "So/P3qKJunwbIw0knMkZm7mK1HWy2+p51O24D1fa1ipqKONZI+le4vduW08x0/EK9wMvq9Jv2k+L\n" +
            "TSSLTXRv2gdeXLuJx5Y7iwpbjTV8CyRy5Ze1S/j6QJkcSQAhiq9pMAAAABQkXqK5TkIoee+LcHL1\n" +
            "F4t8TRqKTGrj/I6Lxi//ADH+Jzam/wBRH+alTX+N7L0Lp9uZZYzJbekxOm2xssZloYcpOY3SfNV6\n" +
            "p0sfylisMbMxzfWmoPMyNBCxsGsNRLSQNBC245jIzSTNJJuyM2DH+zMuLLSNW165HZLXTeSoFU0P\n" +
            "RNq506z4nRJvt4qOTk2FFtzZEukL0kZOk08flSLe4ku5cgu6MizYx4lV6VPlWo4+ZUmzQx8uNVUw\n" +
            "tlpu5jPKp2eFj1crkV6WF7nentczx9WLHl+91k8t3mdpGyzY9IauucFqs808zLuXE8x1s3Orppfc\n" +
            "7G+1/wBXbuDdbLJZ+XIzNuOn4nL+DMLLZcmXuOodxQqAAAAAAAAAAAAAAAAAAAAABEkALasj5tJI\n" +
            "vuU8ya6tzUF+kXHHLceoJMsdpyfi9p+SopvqEKqSNO4W3n6dqDwWR8Y2U9Bq3Ohy9ynk62vNDc4e\n" +
            "TlksniepLDM01phZurFRXqUz5YqupuXUsxbZGduVJlGzGBj+4yscHkY/Tr8fIMxJSLdRI1WZIlDI\n" +
            "2XxIqF2iUsHqyxrW0jSxruOVs0tuq9u1lY7ouNTG0THPdXaZ5UjTxrtN3FYzWj9SRVNMsEzbjapI\n" +
            "8lyU4XQ1M9BU5Rt0nUtM6pjr4VgmbceZsaGey249wjyk9IqNHju7SO3LJTTny9pynidauXV+ZVdp\n" +
            "zuNuXMrN2noDU1oW82xo8dxw67WiotlS0cq9x3OFm2hoZp9Oz8M9awV9MtvqG9ZTpyttyPKWmrm1\n" +
            "qvEc6tiuWJ6bstxiudsjnjbLab1NWWSVhluxHUu0N0ESp8xbLb0n1VJ+H9D6WIKpMAAU23NiVCnJ\n" +
            "l2gWlyrUoKSSeZtqqebteahkvd5k8Vb002nfNZUklfYZI4csjzXXWypjrZIORI0mXtAtaGhlr6pY\n" +
            "IVyZjZqrhzeYsWWLqU3jhboOWnlW61kXxxY64sMWO6Jf2geVblpe6Wz/AFEDf4qYndH8T1tcLVSV\n" +
            "9M0U0C7vicL4gaDayyNV06+mwFroPW9TZ6uOCSTKNm7j0Hb66KvpI542yVlPIkbNDJkvad64R3yS\n" +
            "utawStk3gwHTMhkMiOXaBUAAAAACk7fwYqlvUNjGzAcF4vSrJf2X4nPaPdVR/kbdxQrFqdTN4r7T\n" +
            "XbPT865xp8lMf6mN3jTMOVnjyGor9BbqJlVtxZ1F6gstlWNW3YnN7hdam6zNk204s4/Tq45UblWy\n" +
            "19S0jNtFtpJK2rWJV2lHu5a9R0DROn+XH5mZTPVa43rYrDblt1AuK7i8yykKnxXpKK+45P7LSxZW\n" +
            "+JHqYlkU22mWlHSxJV5kmKj/AG8i8tNPzGyMnHx7MeStWXoYOTGVKyoWkp2lkbFVK6ripzvipqJr\n" +
            "dbmgjbcx38c6y5GStnPeI2s5bxcWpqdv5df/AGNTsdqqbvcY4IV7izXmVtXj3SMd64b6KjoKBamo\n" +
            "X1DKNr0rY47Pa441XFsTN/7gXbtUl3ASAAAAAAAAAAAAAAAAAAAAAAABB+kx1wtkFzompplyVjI+\n" +
            "I/QlLn1Hwwt9JcfM4tib1Swx00KxR9KlfE+dwlSnMuUeJrNwpuTJkbUxi7pScxTSzY9mbHk1YFek\n" +
            "KPttiSZcTiOrPoyxBEqdRCarUXaKqkir6Zo5AxHLbiX6Vrs5TqSwz2ytZlX0zF0NbJRT8yNjslys\n" +
            "8FzoGWRdxym9WWe2TsuO035zTXlj2b9pfVUdfCsFQ242bkrjlH0nC6Wplp/VjbFjetN612rTVTFV\n" +
            "h8q1pvCnLeJ0lNzcY8eYdGuV4pKa1tUrKvScFv13ludyknZviVwsNT6aWapYvpk2nduEM9dJQYzZ\n" +
            "cs4zZbXPc7nHBCuTZKx6d0zao7XaY4I1x2nZ28tNle4ku4+LHiSVcTGJgAoAAAKf+4VCLLkBSaNe\n" +
            "ltysYqTTNtap8y0C5fiZrEYgWlPGsa+muMaldfU3EhjuAizctdxreuqaOp0/JkuRsjMqmn8QL5TW\n" +
            "qyyZN6jbcQPONQuM0n5HUuDM/LuXL7cTlckmVRI3uY6pwXpJWuPPb7eIHbVbuPvcF9pICQAAAizY\n" +
            "keZuAqGKvlalBbZppPaxkczm3FrU0dFaWoYZPWZgOK36tauuk0re9j7YZuTW8xjGyNzJGb3GUttI\n" +
            "zR5GOvMsmOfTOVlfPcZMWbaW7NyfTj6hly+nqM9pvTclfUrPMu051VMuvM6yutK6bkq51nmXadIV\n" +
            "VpoFgjKdHTRUFIscakmXJsjnZKrZjmtkWyXpJNtGRT6jHPpk+Qk24MuKkctpFefon0Rrk2JsVtp+\n" +
            "XGY220iyNkxno1xXE7HHxtDNk2SOP8ZqKVv5lftnYmNH4qQxyaUZvkdVovPtrmWO5wu3uU9S6bqU\n" +
            "q7PDJH04qeVKf/Wrl7j0xw9ybTUfMCm1MRUkMQJAAAAAAAAAAAAAAAAAAAAAAAAESQAEe4kRAixC\n" +
            "SNWUqkTFQ1W5U7QzZFFWyU2KupFmU12SPkz4nE5GHrdDDkS+JEN1A0pbeuyRHuBLJRVKFkkWT4lG\n" +
            "7WqmusDZLuKyt2jJo2MssOrkt+0/PbKlmVfTMPGyt6i5Kynbq63QXGmaOReo5XrKwrYcmj7jfw5O\n" +
            "zyZK1lrNwv1bNTtTNLtMQiNK3hjuZvE+MzMzm6cOdLNersski+mp1ZnWXKqtm+cK9HeUpFuVUvqH\n" +
            "VU8PDwT+BbUdMtJSLBGuOJdKZEpESRECQAAAAAAAABBpI4+plUCCtku0Kq5bTFXLUlttiM0064/F\n" +
            "jQtQcXqGkjaO1s3M+Sgb7fNQUllopJqiVcjzprDVNTqK5SSs3p9OJb3zVVwv0rNUSNj8TCxxtM2K\n" +
            "qzMwFxQ0clXUxwRrlIzHozh/ptbDZ48l9Rtxp/C/QvLVblWRep8jrqqvLVV7QKo7iIXoAqAACLFN\n" +
            "m7So3SUZtsMjd2IGF1BqKks9JIskvqYnm/Ut6nu90klmfJcjMa8udbNfpI5mZVNPXc38SpFSnj5j\n" +
            "YmwQq1OqxxrlmUbPamuLLyeo6Rp3R25Z6pTm8jNq3sONidL6Ukr5FlqF2nRqekgt0KxRruK0awUU\n" +
            "fLhUpr1ZMc3Jk2bOwzZESTNkwNWa2VrqEencCJXyyT6SjbmNuIxrzJ8VJN04qZa027H1GNjHj7Gv\n" +
            "krrXtDT8pFL0KuIO3jx6uZVByvjBflhoPpsbbssjp1VJy6aRvap5n17dZLnf5GZtq7TKxsDQwvU1\n" +
            "0aL1MynqHR9I1JY4Y29pwrhrp9rreFkZdqno2nj5UCxr2qUpVJESQAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAARABIAAoU2XaYW6UGW5TOEZFWRcTWzY+yVTWrT1bFuWxHLcZS4UHL9VTGquPUcTJj63Vw5NpG\n" +
            "JKRZgYddlKhEZDqEnyNJ5aNvaq5HGddaia7VrRZbVOha2vX0q1tGreoxxOomaeZpG6mY6vCw6+mh\n" +
            "mrZKjgaqqY4lXczYnpDh/piKx2WPJfUbccr4WaZ+p3VaudfS8Tv0caxxqq9KriddqqnaSUiy7SSk\n" +
            "gSIkgAAAiwxDAAUppFjjyZscSplic24pata2UDU1O2MzAZHUPEi1WTJPGRnmX2nML9xTulxZlhbF\n" +
            "TR6ipkq5GlkZmZhDTz1kvJjjyb4gVqy81te3rTt+4s9ze5mNytPDW83B1xjVV+Rvlj4Qx0zLJXdX\n" +
            "5AcltOnbhdJ1jp4G3e5TrWjeF0VJjV1y7jodrsNBbI1WGJf2mTxxUCjS00FNCsUK4qpcKRVl7SSg\n" +
            "SAAAAARbpKPUrZFWRclPm3qUDlPErQPmYWuFGvqHE5oXp5WjkXFlPX1RGskLKy5KxxPiZoVaSRrh\n" +
            "Rx7e4DTdI3xbVXq0n2zuVLXwV9IskOPLPNrZRybe06BoPUzU1StJUN6ZocvDt6bWHI6oqrkFbdiR\n" +
            "XHFZFbaxHHLcxxK/q3ZlLHEjlkSyDDXVm+gj9okrbStS0zVMm4zTPYx1XWlQ0jTSZGyQrjHiU6Wk\n" +
            "WFS46Tr8fD1uZmydiOXqYjp2qMd2Ri9QXX6RbJKljdlrsZrbUFNZ7PJlJuZcTzVXVHmq2SX3MZ3V\n" +
            "uqqm+1smTty8i20zY575do4I1bHqJHVuD9qljovNyLidWUxNlti2q1x00a47TKx9JSkwAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAEQSAEQSAFI+YlQ+Ez5SozQ8xMWMBcqVoWy7TZWLaqgWZcWNTkcecjYx5\n" +
            "NWqx4soXdtK1VSNSSZL0lFW5m5TkVNT5dGa2F3MGk5cbM3aS+2a7ra6rarUyq3qMThnajJ8ub64v\n" +
            "klzuzKrbVNbo6dqipjiXdkxCeZp52kbuY33hbpv6ndlqZF9NT6THOsuVt6dZ0Hp9bPZY1ZfUbcbV\n" +
            "j+0p08awqsa9qlwey8pH9GyCqTB68RJAAAAAKbN2kmKbdIEm3KcR4xUWM/mWO2M2KnBeLl6WrvDU\n" +
            "ittVQOc9207Jwj03TSUn1CaLJsu44urYyZHeuEt8pprP5LLGTLIDpEcMUa+mqqSbFuoiuWPpksQC\n" +
            "qpUxIJ/Uqf8AYIlH9GPqkgFgAAAACDfI+MrdpUAFLFsdpZ3C3QVdJJBIuWSmRKcgHmnXml59P3WR\n" +
            "lX0WNXp5pKWZZFbcemNXaZgv1rkjZfU6jzfeLZParjJTTL0sK9E+XXND35brRLBI3qKbVly9pwHT\n" +
            "13ntdwjkV8VO5W25x3eijkj9pxOTxta2l08ObZeKpFW3bhJ1ZL0kl9bappTtk+mbbUhjaSfb0my0\n" +
            "dKsaqWttoFgXKTqMovQdXj4ZloZsmxifT6ffE6LVlHEwmqrY11s7Uy9RnOo+Hs+R53XhhdJLoysq\n" +
            "8vI65o3RVNp+mWRl9Y2rlx+1SXbtPQXIkp8Xap9UCQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "iACKoCmyk/A++J79ErKoplmjxYwFwopKVvTNqKM0Mci7jU5GHaWxjza01LLGBpJO45Bry+fUbo0a\n" +
            "t6aqdS19Wx2W1yNG3qMcAqaiSpqGdu5jHxuLrTNk5Gz7R07VdWsC9zHo/h7p9bLY1Vl9RtxyThfp\n" +
            "36rfFnmX0VPQscfJjVO1VxOn8y59JxlYpKu4qmKVAALAAAAAAKbEm6Sn1LtAtLrVpRW+aaT2seXt\n" +
            "TVvn7xNPlluO68UrutBp5o1bdkedpG5kzN7ipFPIzmm9QT6fukc8bbe4qWnStTdaBqmFekxNVSS0\n" +
            "MrRSK2Rjmp2NXpXTOr6HUFNHyZMWx7jYssmxU8oWm91dpnWSnlZTplh4wNCqx3L/AIqMn/k2dmVv\n" +
            "UxKpq1p11abnjyZcWb3GxrUIy5KysvxPf4FcFPLaSU9EgAAAAAAACmy7sioQ8V/VgIbWOT8VtHc6\n" +
            "NrlSxbjrOBb11JHW0kkEi5KykpeRPHKN8W7TonD3UC00nkJm29Rhdeadax3yRVX023Gt0tS1NURy\n" +
            "RtjixOTHtLNjyavRkMcs2Kx9LGdoLYsC5N1GC4f3iC72WOXJeYu03DH2mpPHZKyI4lQZbsT4bszq\n" +
            "1apLwPp88D6P5JRyG4kMSlIYt3ExiMQAUkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAiACKgA\n" +
            "AJkfCjIyqrM3aVTWdcX5bLZZJMtzbT1OuzkvFTUXn7s1JG3pqpz6niaoqFiXuYq3CqatrZJ2bczG\n" +
            "2cN9NteLssjLtUrY1de4d6fWz2WNmX1GNw6ilTx+XpljVelSr3ZEbbKSjKhTjKhYAAAAAAAAixT+\n" +
            "3GxUYsrlVLSUUkze1gOKcYL15i4eUVjmtOuUi49zYmW1dcfqd9mny+JT0xQefu0cfiv8F3E0OxaN\n" +
            "ty2yxrkq7iz1VoqC6xtPTr6hslPH5amjXtVSpk2W0+dnNU53Vx49pcCuWnK63SePMjb9pi3Rl6lx\n" +
            "PRtRSUlauNREv7jWblw/tdarNGrKx0/z529NPJx/TjcNbPSvlDIy/wCR1Thzr5o2Wkrpf3Gp37Q1\n" +
            "dbFaVVXkqamjvC+XgzKysdHs7PTBU6vXUNXFNGskbKyt7S5T9Mf4HIuEeopahfKVEuX5HXI227gl\n" +
            "UAAAAAAAAIkiIAAEjSOIGmVvVpkZV/mF3HnSsp2pKuSJu1j1zMvMVlbuXE4NxQ0o1sr2rY19NjIL\n" +
            "HhvqiWz3hYGb0WPQ9LULUwLOvSynkSnlaOZWVscWyPQnDHUy3W0rBI3qKYxvm3qJKRxPq7glMAFK\n" +
            "SAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARKbblKjEWbFQKcjctWl9qnB+K2pvO1\n" +
            "7UULbWOra01BHYbHJIzbm2nmm4VTVdXJPJuZmAowRNUTqvubE9FcNdO/SrOssi4yMcl4c6be9XuN\n" +
            "mX0V3Ho2nhWnp44l7VxArkgAAAAAAAAAIH3xPh98ST+EWNO4kXVaDTkm7FssTcW6Th/GK9c6v+nx\n" +
            "ttxyKlLltRJzp3f3MdC4Y27mVPmWU59HHlMq+Hcx23RNu8hZ1bHqNPl5NMflucefTZlI9wUKxw6q\n" +
            "m/5FUdIyBi9K8re6Uy1tukjZe04HfqLyVzkiPQmOSsvxON8QqDk3hpFXbidThZK21/lqcmVtoCva\n" +
            "i1HG+eK+O09MUsnMpo2+J5It1Q1NXwyL7lPUelavztlhky7VOzk9OezIHSCPkVAAWAAAAAAAAKbK\n" +
            "uJgNXWRL5Z5IGXpXIz/dkG3KwHkW6UUtBXyQSLiysZ7QGovod7jZm2vtNq4uaX5FW1yp129Jy2Px\n" +
            "5cysvaB67oapaumjnjbJWUuo+k5twt1F9RtK0UjeorHSl6QJAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAixRmZY4WZuldxWZcjT+IF+W0WWTFsW6QOW8VtTfUbo1JC3pqc/hjkmmj\n" +
            "VVyZmxJV9XJWVcksnjlkxuXDPTbXW8LPIvoqB1bhvp/6LZY+YvqNuN2Yp08awwLGvapUX5AVAAAA\n" +
            "AAAAAABTyCtkO4pq2MZIoV9StPRTO3arHl/VNza53iabxbLFmU7vxIvK2zT0m71GPOU7cyZm9zFs\n" +
            "f8Mnpmi85do4sTvFPCsNFHGvtOc8L7C1XV+bZdp1RqCRjl8vavl0sOq3yIqyqu4uvpkpL6ZKczry\n" +
            "NnbGs8hkXX06UfTJR15FbY1qaDxKol8rzzpH06U1vXllaTT7S49Ju8bHW2zBkqXCftzL8T0Dwruf\n" +
            "m7AseW7I8+y7Zm/+GOvcGK7KTxpcvkddy6dky7T6F3NkfO48yfKlUAFgAAAAAAACmy7dp8ZWx2lU\n" +
            "AYDU9kiu1nkgZctuR5kvFue2V8kEnVketZFyjZW7jhnFzTPlKtrhCu0DV9A35rNe42ZtrbT0tQ1K\n" +
            "1dJHKvcp5Dhk5LK3crHojhjqJbnZ1gZvUUDfMhkR7T4vVkBVAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAEWbEp5MvV0gfKmRYIGkZulTz1xI1M14ujU0bemp1HiRqaK02eSJW9ZjztUTyV\n" +
            "E0jN3NkAo6aSrqo4I1y8WY9IcP8ATq2Wzx5L6jbjlvC3TMtxui1ci/y6noGGJY41Ve1cQPq9AX7h\n" +
            "9VPBT7iBIAAAAAAAAAAUyMmOJ9+KlrcJ1pbdNIzdrAlxbjBelqK/ykbbTmNPG01RHH7mMtqq4+fv\n" +
            "U0rNluK2i7V9Vv0ceO1dwHduHdoW2afjyXcxtyxr7Shb6fy9JHF7VUul3GLUfOWp85a+0niMStRD\n" +
            "lr7Ry19pMYk6imyqYnUlItXZ5ose0zPiv6lCsXKmk/EqZHky70/lrjNH/Z2Nx4S1/l9RqvxMHriH\n" +
            "kX6RSWh6nymoY2Usl6ej+2pPx6ijStlSRt8VK+J5XopUAB6AAAAAAAAAAAiy5Gt62sq3ixyQY5Mu\n" +
            "42Rimy8yNlbuA8h3GikoK2SCTtY27hnqD6PfFWRvTbaX3FbT7UF4arjX02NAppmgqI5FbHFlYD19\n" +
            "TzLPCsi9yk+01XQt8W72GNmbcu02pfkBUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAF\n" +
            "NlyLeuqFpqSSWRsVVS4boOZ8VtU+QoGoYZPUYDmPEDUj3y9yYt6a7TXbbSy1dbHBCuTMxbszSSNl\n" +
            "uZjqnCXSqy1K3KojyXpA6bomwrY7HHAq7m3GyqU1XHHHpKigSAAAAAAAAAAAAAUe7I03iVd/p2nJ\n" +
            "N25mxNybarHD+MV851X9PVtvUBy+Tx5kjs3cx1jgzZuZN41zLt6Tk8MfMqI19zHpbh3Z1tmnI1xx\n" +
            "ZtwG2KSVcSJUAAAAAAIsQkXJWX4lUpgec+KlF5fUrN24mpWibkXOGT+zqdN40UirW885TStjUx/k\n" +
            "B6ysc/mLXC3xUyRq3D+p8zp6NmNpAkAAAAAAAAAAAAAixTbapUYiwGl8SrGtzsLMq7l3HnCoj5M8\n" +
            "kftY9e1kK1NNJAy9SnmfX1k8bPf5I/BdrbgNo4R37ytYtFI207su5VY8k2Svkt1zhlRu5T1Hp+v+\n" +
            "o2mGdWy2qBlwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAh3YkyDdQHzp2qF29Q+RCSRY1aRm\n" +
            "xVQMdfrxBaLZJUyNjtPM+pr7LerpJLI23I3jilq/ztW1tp5fTU5eitNJ4KvcwGQ0/apbrc44I1y3\n" +
            "HpzTNnistnjpo1+RovC3SC0VItwqI/UOnQ5LHuAqElI/EkoEgAAAAAAAAAAAAFhcKtaWkmZvax5e\n" +
            "1Xc2uN8mlyy3ZHduJl6W2aebFvUZsTznNJzJGbuZgM5o21Nc79DFjljuPT9DCsFJHEvaqnHeDtjy\n" +
            "m89IvxO0qoDHaSUKSAAAAAAIsRbapJiLdQHLOMlBzLX5n5HDY9sy/wDwx6O4qQ87TTKvuPOMn8JG\n" +
            "/ID0TwprVm08q/I3z/cOR8Fqn9aLxg+R14CQAAAAAAAAAAAAAAAByXjFYeZSfUFXd0nWjBartSXa\n" +
            "yzQMvbkB5UX02y9rHeeEN+81a1ombd4HELpSNQ180LeHcxtnC+7vQahVMtrKB6QBCFuZCre5SYBS\n" +
            "QAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAApyNjuKhTYCKtiu40XiNq+CzW5qaOT1m9psmoLrBY7\n" +
            "dJUzMea9TXqW9XSSpZmZekDF1VQ9XVNKzZMxt3DvS8t8vCsy+iprNntk9zr44IVy3HpPRum4rHaY\n" +
            "1VcZOoDOUdHFSUywKuOKlzifI9xVApKrdRVAAAAAAAAAAAAAQbx/RfHwJlKo/jTyfiBwTi5fvNXZ\n" +
            "qKNvTVTnNNHzqmNMepjaNfUFTFqOTmRsylxw503Ndr0rSRemu7coHatA2j6ZYY1ZcWbcbTuxKNLA\n" +
            "sMEa+1cS5AipIAAAAAAAixFm7SREDA6yp1msM2XtPLlUuNVKv9pGPV+oIedaZl+LHle6py7jMq+5\n" +
            "gOlcG6pVuWPxO4Zbjzvwnn5eoVX4noaFslUCsAAAAAAAAAAAAAAAAUKhcomX3FcoNi0mPaB564n6\n" +
            "elob206xty2XtMHo+kqZL9Hy42PSF0sNJeI8axcizs+jLXZ6nn08e75AZmiVlo4svapcn1f6eB9A\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARYkU1A5XximqY7ZiuWOXacRWJpZlWNWbI9XX6w0l+\n" +
            "omgqFyNTtvCy10Vbz2VuoDF8LdHeSgW4VUW5jqnTtUhT08dNCsUa4qpVxAKSI4kgAAAAAAAAAAAA\n" +
            "AAR7SQA1+7aWtd3k/mItxWtNjpLVHjSxYsZjE+YeAHyPduKhHEkAAAAAAAABFiLFQjiBbVlPz6SS\n" +
            "L3KeeNYaOraS7SNDEzKx6Px3FvNQU07epErf4gca4X6Qq461a2oXE7Uq4qqnyGlhhXGNFX8SriBI\n" +
            "AAAAAAAAAAAAAAAApsVCOIFNcchj6hUxGIEgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAixI\n" +
            "g27aB8x9oYLtJASAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
            "AAAAAAAAAAAAAAAAAAAAAAAAf//Z";

        private  static String imagepath="C:\\Users\\lenovo\\Desktop\\test\\";

    public static void main(String[] args) throws Exception {
        System.out.println("开始插入图片");
        //创建Excel工作簿;
        WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\lenovo\\Desktop\\test\\34456.xls"));
        //创建Excel电子薄;
        WritableSheet sheet = workbook.createSheet("插入图片演示", 0);
        Label labelC = new Label(0, 0, "This is a Label cell");
        String[] header={"设备号", "时间点", "体温", "可见光图片", "红外图 ","地点"};

        List<Map> list=new ArrayList<>();

        Map map=new HashMap();
        map.put("device_sn","22222");
        map.put("collection_time","2020-02-16 20:06:29");
        map.put("temperature","37.4");
        map.put("thermograph_img",image);
        map.put("normal_img",image);
        map.put("road_no","北门");

        Map map1=new HashMap();
        map1.put("device_sn","22222");
        map1.put("collection_time","2020-02-16 20:06:29");
        map1.put("temperature","37.4");
        map1.put("thermograph_img",image);
        map1.put("normal_img",image);
        map1.put("road_no","北门");
        list.add(map);
        list.add(map1);

       addHeader(sheet,header);

        addData(sheet,list);
        //图片路径
        String[] filePaths = new String[1];
        filePaths[0] = "D:\\download\\猫和老鼠.jpg";

        boolean b = Base64Utils.Base64ToImage(image, imagepath+"wq.jpg");


        //将base64编码转存图片放到本地,然后

        //调用图片插入函数
        //addPictureToExcel(sheet,filePaths,3,3);
        //写入Excel表格中;
        workbook.write();
        //关闭流;
        workbook.close();
        System.out.println("恭喜，图片插入成功！");
    }

    private  static  void addData(WritableSheet sheet ,List<Map> list) throws Exception{
            Map map=null;
            Label label=null;
        for (int i=0;i<list.size();i++) {
            map=list.get(i);

            Set  set = map.keySet();


            Object[] strsTrue = set.toArray(new Object[set.size()]);
            List<String> tbList = Arrays.asList( );
            List list2= new ArrayList<>(Arrays.asList(strsTrue));


           /* map.put("device_sn","22222");
            map.put("collection_time","2020-02-16 20:06:29");
            map.put("temperature","37.4");
            map.put("thermograph_img",image);
            map.put("normal_img",image);
            map.put("road_no","北门");*/
            for(int n=0;n<list2.size();n++){
                String key = list2.get(n).toString();

                switch (key){
                    case "device_sn":
                        label= new Label(0,i+1,map.get(key).toString());
                        sheet.addCell(label);
                        break;
                    case "collection_time":
                        label= new Label(1,i+1,map.get(key).toString());
                        sheet.addCell(label);
                        break;
                    case "temperature":
                        label= new Label(2,i+1,map.get(key).toString());
                        sheet.addCell(label);
                        break;
                    case "normal_img":
                        boolean b = Base64Utils.Base64ToImage(image, imagepath+"normal_"+(i+1)+".jpg");
                        String[] filePaths = new String[1];
                        filePaths[0] = imagepath+"normal_"+(i+1)+".jpg";
                        insertImg(sheet,filePaths,3,i+1);

                        break;
                    case "thermograph_img":
                        boolean a = Base64Utils.Base64ToImage(image, imagepath+"thermograph_"+(i+1)+".jpg");
                        String[] filePaths1 = new String[1];
                        filePaths1[0] = imagepath+"thermograph_"+(i+1)+".jpg";
                        insertImg(sheet,filePaths1,4,i+1);
                        break;
                    case "road_no":
                        label= new Label(5,i+1,map.get(key).toString());
                        sheet.addCell(label);
                        break;
                }

            }
        }

    }

    private  static  void addHeader(WritableSheet sheet ,String[] str){
        Label label=null;
        for(int i=0;i<str.length;i++){
             label = new Label(i, 0, str[i]);
            try {
                sheet.addCell(label);
            } catch (WriteException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     *
     * @Title: addPictureToExcel
     * @Description: TODO(将多个图片按实际大小，插入同一个单元格,最后一张图如果高度超过了单元格，则压缩高度使之在单元格内)
     * @date 2016年12月16日 下午6:13:52
     * @param @param picSheet
     * @param @param pictureFilePaths
     * @param @param cellRow
     * @param @param cellCol
     * @param @throws Exception 设定文件
     * @return void 返回类型
     * @throws
     */
    private static void addPictureToExcel(WritableSheet picSheet, String[] pictureFilePaths, double cellRow, double cellCol)
            throws Exception {

        final double cellSpace = 0.02;//图片之间的间隔 占比

        double picWidthMax = 0;
        double picHeightSum =0;//空出图片 离上下边框的距离
        ImgFile[] imgFiles = new ImgFile[pictureFilePaths.length];
        System.out.println("长度:" +pictureFilePaths.length);
        for (int i=0;i<pictureFilePaths.length;i++) {
            ImgFile imgFile = new ImgFile();
            File imageFile = new File(pictureFilePaths[i]);
            // 读入图片
            BufferedImage picImage = ImageIO.read(imageFile);
            ByteArrayOutputStream pngByteArray = new ByteArrayOutputStream();
            //将其他图片格式写成png的形式
            ImageIO.write(picImage, "PNG", pngByteArray);
            imgFile.setPngByteArray(pngByteArray);
            // 取得图片的像素高度，宽度
            double picWidth = picImage.getWidth() * 0.15;  //具体的实验值，原理不清楚。
            double picHeight = picImage.getHeight() * 15; //具体的实验值，原理不清楚。

            imgFile.setHeigth(picHeight);
            imgFile.setWidth(picWidth);
            //汇总
            if (picWidth > picWidthMax) {
                picWidthMax = picWidth;
            }
            picHeightSum += picHeight;
            imgFiles[i] = imgFile;
        }

        WritableFont font;
        font = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        //设置背景颜色;
        cellFormat.setBackground(Colour.WHITE);
        //设置边框;
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        //设置自动换行;
        cellFormat.setWrap(true);
        //设置文字居中对齐方式;
        cellFormat.setAlignment(Alignment.CENTRE);
        //设置垂直居中;
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);


        Label imageLabel = new Label((int)cellCol, (int)cellRow, "",cellFormat);
        picSheet.addCell(imageLabel);


        //设置单元格宽高
        picSheet.setColumnView((int)cellCol, (int)picWidthMax);//列宽
        picSheet.setRowView((int)cellRow, (int)picHeightSum);//行高

        double widthStart = cellSpace;//开始宽度
        double heightStart = cellSpace;//开始高度
        //插入图片
        for (ImgFile imgFile0: imgFiles) {
            double heigthFact = imgFile0.getHeigth()/picHeightSum;//实际高度
            double widthFact = imgFile0.getWidth()/picWidthMax;
            //图片高度压缩了cellSpace+moreHeight,目的是为了该图片高度不超出单元格
            if (heightStart + heigthFact >= 1) {
                double moreHeight = heightStart + heigthFact - 1.00;
                heigthFact -= moreHeight;
                heigthFact -= cellSpace;
            }
            //图片宽度压缩了cellSpace,目的是为了该图片宽度不超出单元格
            if (widthFact >= 1) {
                widthFact -= cellSpace;
            }
            //生成图片对象
            WritableImage image = new WritableImage(cellCol+widthStart, cellRow + heightStart,
                    widthFact, heigthFact, imgFile0.getPngByteArray().toByteArray());
            //将图片对象插入到sheet
            picSheet.addImage(image);
            //开始高度累加，获取下一张图片的起始高度（相对该单元格）
            heightStart += heigthFact;
            heightStart +=cellSpace;//图片直接间隔为cellSpace
        }
    }

    private static void insertImg(WritableSheet picSheet, String[] pictureFilePaths, double cellCol,double cellRow)
            throws Exception {

        final double cellSpace = 0.02;//图片之间的间隔 占比

        double picWidthMax = 0;
        double picHeightSum =0;//空出图片 离上下边框的距离
        ImgFile[] imgFiles = new ImgFile[pictureFilePaths.length];
        System.out.println("长度:" +pictureFilePaths.length);
        for (int i=0;i<pictureFilePaths.length;i++) {
            ImgFile imgFile = new ImgFile();
            File imageFile = new File(pictureFilePaths[i]);
            // 读入图片
            BufferedImage picImage = ImageIO.read(imageFile);
            ByteArrayOutputStream pngByteArray = new ByteArrayOutputStream();
            //将其他图片格式写成png的形式
            ImageIO.write(picImage, "PNG", pngByteArray);
            imgFile.setPngByteArray(pngByteArray);
            // 取得图片的像素高度，宽度
            double picWidth = picImage.getWidth() * 0.0375;  //具体的实验值，原理不清楚。
            double picHeight = picImage.getHeight() * 3.75; //具体的实验值，原理不清楚。

            imgFile.setHeigth(picHeight);
            imgFile.setWidth(picWidth);
            //汇总
            if (picWidth > picWidthMax) {
                picWidthMax = picWidth;
            }
            picHeightSum += picHeight;
            imgFiles[i] = imgFile;
        }

        WritableFont font;
        font = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        //设置背景颜色;
        cellFormat.setBackground(Colour.WHITE);
        //设置边框;
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        //设置自动换行;
        cellFormat.setWrap(true);
        //设置文字居中对齐方式;
        cellFormat.setAlignment(Alignment.CENTRE);
        //设置垂直居中;
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);


        Label imageLabel = new Label((int)cellCol, (int)cellRow, "",cellFormat);
        picSheet.addCell(imageLabel);


        //设置单元格宽高
        picSheet.setColumnView((int)cellCol, (int)picWidthMax);//列宽
        picSheet.setRowView((int)cellRow, (int)picHeightSum);//行高

        double widthStart = cellSpace;//开始宽度
        double heightStart = cellSpace;//开始高度
        //插入图片
        for (ImgFile imgFile0: imgFiles) {
            double heigthFact = imgFile0.getHeigth()/picHeightSum;//实际高度
            double widthFact = imgFile0.getWidth()/picWidthMax;
            //图片高度压缩了cellSpace+moreHeight,目的是为了该图片高度不超出单元格
            if (heightStart + heigthFact >= 1) {
                double moreHeight = heightStart + heigthFact - 1.00;
                heigthFact -= moreHeight;
                heigthFact -= cellSpace;
            }
            //图片宽度压缩了cellSpace,目的是为了该图片宽度不超出单元格
            if (widthFact >= 1) {
                widthFact -= cellSpace;
            }
            //生成图片对象
            WritableImage image = new WritableImage(cellCol+widthStart, cellRow + heightStart,
                    widthFact, heigthFact, imgFile0.getPngByteArray().toByteArray());
            //将图片对象插入到sheet
            picSheet.addImage(image);
            //开始高度累加，获取下一张图片的起始高度（相对该单元格）
            heightStart += heigthFact;
            heightStart +=cellSpace;//图片直接间隔为cellSpace
        }
    }
}
